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

package com.liferay.portlet.documentlibrary.store;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portal.util.PropsValues;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author Preston Crary
 */
public class CMISStoreTest extends BaseStoreTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() throws Exception {
		Assume.assumeFalse(
			"Property \"dl.store.cmis.credentials.username\" must be set",
			Validator.equals(
				PropsValues.DL_STORE_CMIS_CREDENTIALS_USERNAME, "none"));
		Assume.assumeFalse(
			"Property \"dl.store.cmis.credentials.password\" must be set",
			Validator.equals(
				PropsValues.DL_STORE_CMIS_CREDENTIALS_PASSWORD, "none"));
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_user = _addUser();

		Role admin = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		RoleLocalServiceUtil.addUserRole(_user.getUserId(), admin.getRoleId());

		ServiceTestUtil.setUser(_user);
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		ServiceTestUtil.setUser(TestPropsValues.getUser());

		UserLocalServiceUtil.deleteUser(_user);
	}

	private User _addUser() throws Exception {
		User user = UserLocalServiceUtil.fetchUserByScreenName(
			companyId, PropsValues.DL_STORE_CMIS_CREDENTIALS_USERNAME);

		if (user != null) {
			UserLocalServiceUtil.deleteUser(user);
		}

		boolean autoPassword = true;
		String password1 = PropsValues.DL_STORE_CMIS_CREDENTIALS_PASSWORD;
		String password2 = PropsValues.DL_STORE_CMIS_CREDENTIALS_PASSWORD;
		String emailAddress =
			RandomTestUtil.randomString() + RandomTestUtil.nextLong() +
				"@liferay.com";
		long facebookId = 0;
		String openId = StringPool.BLANK;
		String middleName = StringPool.BLANK;
		long prefixId = 0;
		long suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendMail = false;

		return UserLocalServiceUtil.addUser(
			TestPropsValues.getUserId(), companyId, autoPassword, password1,
			password2, false, PropsValues.DL_STORE_CMIS_CREDENTIALS_USERNAME,
			emailAddress, facebookId, openId, LocaleUtil.getDefault(),
			RandomTestUtil.randomString(), middleName,
			RandomTestUtil.randomString(), prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle,
			new long[] {repositoryId},
			organizationIds, roleIds, userGroupIds, sendMail,
			ServiceContextTestUtil.getServiceContext());
	}

	@Override
	protected Store getStore() {
		return new CMISStore();
	}

	private static User _user;

}