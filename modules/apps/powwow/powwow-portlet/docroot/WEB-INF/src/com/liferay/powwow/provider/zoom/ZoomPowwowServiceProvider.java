/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.powwow.provider.zoom;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.powwow.model.PowwowMeeting;
import com.liferay.powwow.model.PowwowMeetingConstants;
import com.liferay.powwow.model.PowwowParticipantConstants;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.provider.BasePowwowServiceProvider;
import com.liferay.powwow.service.PowwowMeetingLocalServiceUtil;
import com.liferay.powwow.util.PortletPropsValues;

import java.io.Serializable;

import java.time.ZonedDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

/**
 * @author Marco Calderon
 * @author Tang Hieu Ha
 * @author Vu Ho
 */
public class ZoomPowwowServiceProvider extends BasePowwowServiceProvider {

	@Override
	public int getAddPowwowMeetingStrategy() {
		return ADD_POWWOW_MEETING_STRATEGY_EAGER;
	}

	@Override
	public List<String> getBrandingFeatures() {
		if (_brandingFeatures != null) {
			return _brandingFeatures;
		}

		List<String> brandingFeatures = new ArrayList<>();

		brandingFeatures.add("fastest-performance");
		brandingFeatures.add("highest-quality");
		brandingFeatures.add("trusted-security");
		brandingFeatures.add("supports-windows-and-mac");
		brandingFeatures.add(
			"includes-audio-video-chat-screen-sharing-and-native-ios-android-" +
				"support");

		_brandingFeatures = brandingFeatures;

		return _brandingFeatures;
	}

	@Override
	public String getBrandingLabel() {
		return "preferred-solution";
	}

	@Override
	public Map<String, String> getIndexFields(PowwowMeeting powwowMeeting) {
		Map<String, String> indexFields = new HashMap<>();

		Map<String, Serializable> providerTypeMetadata =
			powwowMeeting.getProviderTypeMetadataMap();

		indexFields.put(
			"zoomHostId", String.valueOf(providerTypeMetadata.get("host_id")));
		indexFields.put(
			"zoomMeetingId", String.valueOf(providerTypeMetadata.get("id")));

		return indexFields;
	}

	@Override
	public String getJoinByPhoneAccessCodeLabel() {
		return "meeting-id";
	}

	@Override
	public List<String> getJoinByPhoneDefaultNumbers() {
		if (_joinByPhoneDefaultNumbers.isEmpty()) {
			getJoinByPhoneNumbers();
		}

		return _joinByPhoneDefaultNumbers;
	}

	@Override
	public Map<String, List<String>> getJoinByPhoneInternationalNumbers() {
		if (_joinByPhoneInternationalNumbers.isEmpty()) {
			getJoinByPhoneNumbers();
		}

		return _joinByPhoneInternationalNumbers;
	}

	@Override
	public String getPowwowServiceProviderKey() {
		return "zoom";
	}

	@Override
	public String getPowwowServiceProviderName() {
		return "Zoom";
	}

	@Override
	public boolean isFieldAPIKeyRequired() {
		return true;
	}

	@Override
	public boolean isFieldSecretRequired() {
		return true;
	}

	@Override
	public boolean isServerActive(PowwowServer powwowServer) {
		try {
			JSONArray jsonArray = getUsersJSONArray(powwowServer);

			if (jsonArray != null) {
				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	public boolean isSupportsJoinByPhone() {
		return true;
	}

	@Override
	public boolean isSupportsOptionAutoStartVideo() {
		return true;
	}

	@Override
	public boolean isSupportsOptionPassword() {
		return true;
	}

	@Override
	protected Map<String, Serializable> addPowwowMeeting(
		User user, PowwowServer powwowServer, long powwowMeetingId, String name,
		Map<String, String> options) {

		String hostId = getHostId(user, powwowServer);

		List<String> resourceParams = new ArrayList<>();

		resourceParams.add("users");
		resourceParams.add(hostId);
		resourceParams.add("meetings");

		String password = options.get(PowwowMeetingConstants.OPTION_PASSWORD);
		String optionAutoStartVideo = options.get(
			PowwowMeetingConstants.OPTION_AUTO_START_VIDEO);

		JSONObject meetingJSONObject = _createMeetingJSONObject(
			name, password, optionAutoStartVideo, options);

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, null, Http.Method.POST,
			meetingJSONObject);

		Map<String, Serializable> providerTypeMetadataMap = new HashMap<>();

		providerTypeMetadataMap.put("host_id", hostId);
		providerTypeMetadataMap.put("host_video", optionAutoStartVideo);
		providerTypeMetadataMap.put("id", responseJSONObject.getString("id"));
		providerTypeMetadataMap.put("participants_video", optionAutoStartVideo);

		if (Validator.isNotNull(password)) {
			providerTypeMetadataMap.put(
				"password",
				options.get(PowwowMeetingConstants.OPTION_PASSWORD));
		}

		return providerTypeMetadataMap;
	}

	protected void cleanUpZoomHosts(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting) {

		int powwowMeetingsCount =
			PowwowMeetingLocalServiceUtil.getUserPowwowMeetingsCount(
				powwowMeeting.getUserId(),
				PowwowMeetingConstants.STATUS_IN_PROGRESS);

		if (powwowMeetingsCount <= 1) {
			Map<String, Serializable> providerTypeMetadataMap =
				powwowMeeting.getProviderTypeMetadataMap();

			String hostId = String.valueOf(
				providerTypeMetadataMap.get("host_id"));

			deleteZoomHost(powwowServer, hostId);
		}
	}

	protected String createZoomHost(User user, PowwowServer powwowServer) {
		List<String> resourceParams = new ArrayList<>();

		resourceParams.add("users");

		JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

		userJSONObject.put("action", "custCreate");

		JSONObject userInfoJSONObject = JSONFactoryUtil.createJSONObject();

		userInfoJSONObject.put("dept", _DEPT_API);
		userInfoJSONObject.put("email", user.getEmailAddress());
		userInfoJSONObject.put("first_name", user.getFirstName());
		userInfoJSONObject.put("last_name", user.getLastName());
		userInfoJSONObject.put("type", String.valueOf(_USER_TYPE_PRO));

		userJSONObject.put("user_info", userInfoJSONObject);

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, null, Http.Method.POST,
			userJSONObject);

		return responseJSONObject.getString("id");
	}

	@Override
	protected boolean deleteOccurrence(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting,
		Map<String, String> queryParams) {

		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		List<String> resourceParams = new ArrayList<>();

		resourceParams.add("meetings");
		resourceParams.add(String.valueOf(providerTypeMetadataMap.get("id")));

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, queryParams, Http.Method.DELETE,
			null);

		if (!_isSuccess(responseJSONObject)) {
			return false;
		}

		return true;
	}

	@Override
	protected boolean deletePowwowMeeting(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting) {

		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		List<String> resourceParams = new ArrayList<>();

		resourceParams.add("meetings");
		resourceParams.add(String.valueOf(providerTypeMetadataMap.get("id")));

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, null, Http.Method.DELETE, null);

		if (!_isSuccess(responseJSONObject)) {
			return false;
		}

		cleanUpZoomHosts(powwowServer, powwowMeeting);

		return true;
	}

	protected void deleteZoomHost(PowwowServer powwowServer, String hostId) {
		List<String> resourceParams = new ArrayList<>();

		resourceParams.add("users");
		resourceParams.add(hostId);

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, null, Http.Method.GET, null);

		String dept = responseJSONObject.getString("dept");

		if (!_DEPT_API.equals(dept)) {
			return;
		}

		responseJSONObject = execute(
			powwowServer, resourceParams, null, Http.Method.DELETE, null);

		if (!_isSuccess(responseJSONObject)) {
			throw new SystemException(
				"Unable to delete Zoom host. " +
					_getResponseMessage(responseJSONObject));
		}
	}

	@Override
	protected boolean endPowwowMeeting(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting) {

		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		List<String> resourceParams = new ArrayList<>();

		resourceParams.add("meetings");
		resourceParams.add(String.valueOf(providerTypeMetadataMap.get("id")));
		resourceParams.add("status");

		JSONObject actionJSONObject = JSONFactoryUtil.createJSONObject();

		actionJSONObject.put("action", "end");

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, null, Http.Method.PUT,
			actionJSONObject);

		return _isSuccess(responseJSONObject);
	}

	protected JSONObject execute(
		PowwowServer powwowServer, List<String> resourceParams,
		Map<String, String> queryParams, Http.Method method,
		JSONObject jsonBodyObject) {

		return execute(
			powwowServer, resourceParams, queryParams, method, jsonBodyObject,
			true);
	}

	protected JSONObject execute(
		PowwowServer powwowServer, List<String> resourceParams,
		Map<String, String> queryParams, Http.Method method,
		JSONObject jsonBodyObject, boolean throwError) {

		Http.Options options = new Http.Options();

		StringBundler sb = new StringBundler();

		sb.append("https://api.zoom.us/v2");

		for (String param : resourceParams) {
			sb.append(StringPool.SLASH);
			sb.append(param);
		}

		String location = sb.toString();

		if ((queryParams != null) && !queryParams.isEmpty()) {
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				location = HttpUtil.addParameter(
					location, entry.getKey(), entry.getValue());
			}
		}

		options.setLocation(location);

		String token = getToken(powwowServer);

		options.addHeader("Authorization", "Bearer " + token);

		options.addHeader("Content-Type", "application/json");

		if ((jsonBodyObject != null) && !Http.Method.GET.equals(method)) {
			options.setBody(
				jsonBodyObject.toJSONString(), "application/json", "UTF-8");
		}

		if (method == Http.Method.POST) {
			options.setPost(true);
		}
		else if (method == Http.Method.PUT) {
			options.setPut(true);
		}
		else if (method == Http.Method.DELETE) {
			options.setDelete(true);
		}
		else if (method == Http.Method.PATCH) {
			options.setPatch(true);
		}

		try {
			long elapsedTime = System.currentTimeMillis() - _lastAPICallTime;

			if (elapsedTime < Time.SECOND) {
				if (_apiCallCount >= 10) {
					try {
						Thread.sleep(Time.SECOND + 1 - elapsedTime);

						_apiCallCount = 1;
					}
					catch (InterruptedException ie) {
					}
				}

				_apiCallCount++;
			}
			else {
				_apiCallCount = 1;
			}

			if (_apiCallCount == 1) {
				_lastAPICallTime = System.currentTimeMillis();
			}

			String response = sendRequest(options);

			JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
				response);

			if (throwError && !_isSuccess(responseJSONObject)) {
				throw new SystemException(
					"Unable to complete request: " +
						_getResponseMessage(responseJSONObject));
			}

			return responseJSONObject;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected String getHostId(User user, PowwowServer powwowServer) {
		String emailAddress = user.getEmailAddress();

		JSONObject userJSONObject = getUserJSONObject(
			powwowServer, emailAddress);

		if (userJSONObject != null) {
			return userJSONObject.getString("id");
		}

		return createZoomHost(user, powwowServer);
	}

	@Override
	protected long getJoinByPhoneAccessCode(PowwowMeeting powwowMeeting) {
		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		String accessCode = String.valueOf(providerTypeMetadataMap.get("id"));

		return GetterUtil.getLong(accessCode);
	}

	protected void getJoinByPhoneNumbers() {
		try {
			Source source = new Source(
				HttpUtil.URLtoString("http://zoom.us/zoomconference"));

			List<Element> countryElements = source.getAllElementsByClass(
				"country");
			List<Element> numberElements = source.getAllElementsByClass(
				"vcl_number");

			if (countryElements.size() != numberElements.size()) {
				return;
			}

			for (int i = 0; i < countryElements.size(); i++) {
				Element countryElement = countryElements.get(i);

				String country = HtmlUtil.extractText(
					countryElement.toString());

				Element numberElement = numberElements.get(i);

				String number = HtmlUtil.extractText(numberElement.toString());

				if (Objects.equals(country, "United States")) {
					_joinByPhoneDefaultNumbers.add(number);
				}

				List<String> numbers = _joinByPhoneInternationalNumbers.get(
					country);

				if (numbers == null) {
					numbers = new ArrayList<>();
				}

				numbers.add(number);

				_joinByPhoneInternationalNumbers.put(country, numbers);
			}
		}
		catch (Exception e) {
		}
	}

	@Override
	protected String getJoinPowwowMeetingURL(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting, String name,
		int type) {

		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		List<String> resourceParams = new ArrayList<>();

		resourceParams.add("meetings");
		resourceParams.add(String.valueOf(providerTypeMetadataMap.get("id")));

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, null, Http.Method.GET, null);

		String joinPowwowMeetingURL = responseJSONObject.getString("join_url");

		if (type == PowwowParticipantConstants.TYPE_HOST) {
			joinPowwowMeetingURL = responseJSONObject.getString("start_url");
		}

		return joinPowwowMeetingURL;
	}

	@Override
	protected boolean getOptionAutoStartVideo(PowwowMeeting powwowMeeting) {
		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		return GetterUtil.getBoolean(
			providerTypeMetadataMap.get("option_host_video"));
	}

	@Override
	protected String getOptionPassword(PowwowMeeting powwowMeeting) {
		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		return GetterUtil.getString(providerTypeMetadataMap.get("password"));
	}

	protected Map<String, String> getParameterMap(PowwowMeeting powwowMeeting) {
		Map<String, String> parameterMap = new HashMap<>();

		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		parameterMap.put(
			"host_id", String.valueOf(providerTypeMetadataMap.get("host_id")));
		parameterMap.put(
			"id", String.valueOf(providerTypeMetadataMap.get("id")));

		return parameterMap;
	}

	protected String getToken(PowwowServer powwowServer) {
		String zoomApiKey = powwowServer.getApiKey();
		String zoomApiSecret = powwowServer.getSecret();
		int timeToLive =
			PortletPropsValues.POWWOW_PROVIDER_API_TOKEN_TIME_TO_LIVE;

		try {
			Algorithm algorithm = Algorithm.HMAC256(zoomApiSecret);

			ZonedDateTime now = ZonedDateTime.now();
			ZonedDateTime toliveDateTime = now.plusSeconds(timeToLive);
			Date expirationDate = Date.from(toliveDateTime.toInstant());

			Date issuedAt = Date.from(now.toInstant());

			Builder token =
				JWT.create()
					.withIssuedAt(issuedAt)
					.withExpiresAt(expirationDate)
					.withIssuer(zoomApiKey);

			String tokenGenerated = token.sign(algorithm);

			return tokenGenerated;
		}
		catch (JWTCreationException jwtce) {
			_log.error("Error while generating token", jwtce);
		}

		return null;
	}

	protected JSONObject getUserJSONObject(
		PowwowServer powwowServer, String email) {

		JSONObject responseJSONObject = execute(
			powwowServer, Arrays.asList("users", email), null, Http.Method.GET,
			null, false);

		if (!_isSuccess(responseJSONObject)) {
			return null;
		}

		return responseJSONObject;
	}

	protected JSONArray getUsersJSONArray(PowwowServer powwowServer) {
		JSONObject responseJSONObject = execute(
			powwowServer, Arrays.asList("users"), null, Http.Method.GET, null);

		return responseJSONObject.getJSONArray("users");
	}

	@Override
	protected JSONObject getZoomMeetingJSONObject(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting) {

		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		List<String> resourceParams = new ArrayList<>();

		resourceParams.add("meetings");
		resourceParams.add(String.valueOf(providerTypeMetadataMap.get("id")));

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, null, Http.Method.GET, null, false);

		if (responseJSONObject != null) {
			int code = responseJSONObject.getInt("code");

			if (code == _ERROR_CODE_MEETING_NOT_FOUND) {
				throw new SystemException(
					"Unable to retrieve Zoom meeting: " +
						_getResponseMessage(responseJSONObject));
			}
		}

		if (!responseJSONObject.has("created_at")) {
			throw new SystemException(
				"Invalid response from Zoom server: " + responseJSONObject);
		}

		String createdAt = responseJSONObject.getString("created_at");

		if (createdAt.equals(StringPool.BLANK)) {
			return null;
		}

		return responseJSONObject;
	}

	@Override
	protected boolean isPowwowMeetingCreated(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting) {

		JSONObject zoomMeetingJSONObject = getZoomMeetingJSONObject(
			powwowServer, powwowMeeting);

		if (zoomMeetingJSONObject == null) {
			return false;
		}

		return true;
	}

	@Override
	protected boolean isPowwowMeetingRunning(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting) {

		try {
			JSONObject zoomMeetingJSONObject = getZoomMeetingJSONObject(
				powwowServer, powwowMeeting);

			if (zoomMeetingJSONObject == null) {
				return false;
			}

			String status = zoomMeetingJSONObject.getString("status");

			if (StringUtil.equalsIgnoreCase(
					_MEETING_STATUS_IN_PROGRESS, status)) {

				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	protected boolean updateOccurrence(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting,
		Map<String, String> options, Map<String, String> queryParams) {

		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		List<String> resourceParams = new ArrayList<>(2);

		resourceParams.add("meetings");
		resourceParams.add(String.valueOf(providerTypeMetadataMap.get("id")));

		JSONObject meetingJSONObject = JSONFactoryUtil.createJSONObject();

		meetingJSONObject.put(
			PowwowMeetingConstants.OPTION_START_TIME,
			options.get(PowwowMeetingConstants.OPTION_START_TIME));
		meetingJSONObject.put(
			PowwowMeetingConstants.OPTION_DURATION,
			options.get(PowwowMeetingConstants.OPTION_DURATION));

		JSONObject responseJSONObject = execute(
			powwowServer, resourceParams, queryParams, Http.Method.PATCH,
			meetingJSONObject);

		if (!_isSuccess(responseJSONObject)) {
			return false;
		}

		return true;
	}

	@Override
	protected Map<String, Serializable> updatePowwowMeeting(
		PowwowServer powwowServer, PowwowMeeting powwowMeeting, String name,
		User user, Map<String, String> options) {

		Map<String, Serializable> providerTypeMetadataMap =
			powwowMeeting.getProviderTypeMetadataMap();

		List<String> resourceParams = new ArrayList<>(2);

		resourceParams.add("meetings");
		resourceParams.add(String.valueOf(providerTypeMetadataMap.get("id")));

		String password = options.get(PowwowMeetingConstants.OPTION_PASSWORD);
		String optionAutoStartVideo = options.get(
			PowwowMeetingConstants.OPTION_AUTO_START_VIDEO);

		JSONObject meetingJSONObject = _createMeetingJSONObject(
			name, password, optionAutoStartVideo, options);

		execute(
			powwowServer, resourceParams, null, Http.Method.PATCH,
			meetingJSONObject);

		providerTypeMetadataMap.put("host_video", optionAutoStartVideo);
		providerTypeMetadataMap.put("participants_video", optionAutoStartVideo);

		if (Validator.isNull(password)) {
			providerTypeMetadataMap.remove("password");
		}
		else {
			providerTypeMetadataMap.put("password", password);
		}

		return providerTypeMetadataMap;
	}

	private JSONObject _createMeetingJSONObject(
		String name, String password, String optionAutoStartVideo,
		Map<String, String> options) {

		JSONObject meetingJSONObject = JSONFactoryUtil.createJSONObject();

		meetingJSONObject.put("topic", name);

		if (Validator.isNotNull(password)) {
			meetingJSONObject.put("password", password);
		}

		JSONObject meetingSettingsJSONObject =
			JSONFactoryUtil.createJSONObject();

		meetingSettingsJSONObject.put("host_video", optionAutoStartVideo);
		meetingSettingsJSONObject.put(
			"participants_video", optionAutoStartVideo);

		meetingJSONObject.put("settings", meetingSettingsJSONObject);

		String meetingType = _MEETING_TYPE_RECURRING_NO_TIME;

		String recurrenceData = options.get(
			PowwowMeetingConstants.OPTION_RECURRENCE);

		if (!Validator.isBlank(recurrenceData)) {
			meetingType = _MEETING_TYPE_RECURRING;

			try {
				meetingJSONObject.put(
					PowwowMeetingConstants.OPTION_RECURRENCE,
					JSONFactoryUtil.createJSONObject(recurrenceData));
			}
			catch (JSONException jsone) {
				_log.error(
					"Error while creating JSONObject of recurrence", jsone);
			}

			meetingJSONObject.put(
				PowwowMeetingConstants.OPTION_START_TIME,
				options.get(PowwowMeetingConstants.OPTION_START_TIME));
			meetingJSONObject.put(
				PowwowMeetingConstants.OPTION_DURATION,
				options.get(PowwowMeetingConstants.OPTION_DURATION));
		}

		meetingJSONObject.put("type", meetingType);

		return meetingJSONObject;
	}

	private String _getResponseMessage(JSONObject responseJSONObject) {
		if (responseJSONObject == null) {
			return StringPool.BLANK;
		}

		return responseJSONObject.getString("message");
	}

	private boolean _isSuccess(JSONObject responseJSONObject) {
		if (responseJSONObject == null) {
			return false;
		}

		if (responseJSONObject.getInt("code") < ERROR_CODE_300) {
			return true;
		}

		return false;
	}

	private static final String _DEPT_API = "API";

	private static final int _ERROR_CODE_MEETING_NOT_FOUND = 3001;

	private static final String _MEETING_STATUS_IN_PROGRESS = "started";

	private static final String _MEETING_TYPE_RECURRING = "8";

	private static final String _MEETING_TYPE_RECURRING_NO_TIME = "3";

	private static final int _USER_TYPE_PRO = 2;

	private static final Log _log = LogFactoryUtil.getLog(
		ZoomPowwowServiceProvider.class);

	private static int _apiCallCount;
	private static long _lastAPICallTime = System.currentTimeMillis();

	private List<String> _brandingFeatures;
	private final List<String> _joinByPhoneDefaultNumbers = new ArrayList<>();
	private final Map<String, List<String>> _joinByPhoneInternationalNumbers =
		new TreeMap<>();

}