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

package com.liferay.powwow.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.powwow.exception.NoSuchMeetingOccurrenceException;
import com.liferay.powwow.model.PowwowMeetingOccurrence;
import com.liferay.powwow.model.impl.PowwowMeetingOccurrenceImpl;
import com.liferay.powwow.model.impl.PowwowMeetingOccurrenceModelImpl;
import com.liferay.powwow.service.persistence.PowwowMeetingOccurrencePersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the powwow meeting occurrence service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @generated
 */
@ProviderType
public class PowwowMeetingOccurrencePersistenceImpl
	extends BasePersistenceImpl<PowwowMeetingOccurrence>
	implements PowwowMeetingOccurrencePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>PowwowMeetingOccurrenceUtil</code> to access the powwow meeting occurrence persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		PowwowMeetingOccurrenceImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByPowwowMeetingId;
	private FinderPath _finderPathWithoutPaginationFindByPowwowMeetingId;
	private FinderPath _finderPathCountByPowwowMeetingId;

	/**
	 * Returns all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @return the matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId) {

		return findByPowwowMeetingId(
			powwowMeetingId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end) {

		return findByPowwowMeetingId(powwowMeetingId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return findByPowwowMeetingId(
			powwowMeetingId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByPowwowMeetingId(
		long powwowMeetingId, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByPowwowMeetingId;
			finderArgs = new Object[] {powwowMeetingId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByPowwowMeetingId;
			finderArgs = new Object[] {
				powwowMeetingId, start, end, orderByComparator
			};
		}

		List<PowwowMeetingOccurrence> list = null;

		if (retrieveFromCache) {
			list = (List<PowwowMeetingOccurrence>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PowwowMeetingOccurrence powwowMeetingOccurrence : list) {
					if ((powwowMeetingId !=
							powwowMeetingOccurrence.getPowwowMeetingId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE);

			query.append(_FINDER_COLUMN_POWWOWMEETINGID_POWWOWMEETINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(PowwowMeetingOccurrenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(powwowMeetingId);

				if (!pagination) {
					list = (List<PowwowMeetingOccurrence>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PowwowMeetingOccurrence>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence findByPowwowMeetingId_First(
			long powwowMeetingId,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		PowwowMeetingOccurrence powwowMeetingOccurrence =
			fetchByPowwowMeetingId_First(powwowMeetingId, orderByComparator);

		if (powwowMeetingOccurrence != null) {
			return powwowMeetingOccurrence;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("powwowMeetingId=");
		msg.append(powwowMeetingId);

		msg.append("}");

		throw new NoSuchMeetingOccurrenceException(msg.toString());
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence fetchByPowwowMeetingId_First(
		long powwowMeetingId,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		List<PowwowMeetingOccurrence> list = findByPowwowMeetingId(
			powwowMeetingId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence findByPowwowMeetingId_Last(
			long powwowMeetingId,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		PowwowMeetingOccurrence powwowMeetingOccurrence =
			fetchByPowwowMeetingId_Last(powwowMeetingId, orderByComparator);

		if (powwowMeetingOccurrence != null) {
			return powwowMeetingOccurrence;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("powwowMeetingId=");
		msg.append(powwowMeetingId);

		msg.append("}");

		throw new NoSuchMeetingOccurrenceException(msg.toString());
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence fetchByPowwowMeetingId_Last(
		long powwowMeetingId,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		int count = countByPowwowMeetingId(powwowMeetingId);

		if (count == 0) {
			return null;
		}

		List<PowwowMeetingOccurrence> list = findByPowwowMeetingId(
			powwowMeetingId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the powwow meeting occurrences before and after the current powwow meeting occurrence in the ordered set where powwowMeetingId = &#63;.
	 *
	 * @param occurrenceId the primary key of the current powwow meeting occurrence
	 * @param powwowMeetingId the powwow meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence[] findByPowwowMeetingId_PrevAndNext(
			long occurrenceId, long powwowMeetingId,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		PowwowMeetingOccurrence powwowMeetingOccurrence = findByPrimaryKey(
			occurrenceId);

		Session session = null;

		try {
			session = openSession();

			PowwowMeetingOccurrence[] array =
				new PowwowMeetingOccurrenceImpl[3];

			array[0] = getByPowwowMeetingId_PrevAndNext(
				session, powwowMeetingOccurrence, powwowMeetingId,
				orderByComparator, true);

			array[1] = powwowMeetingOccurrence;

			array[2] = getByPowwowMeetingId_PrevAndNext(
				session, powwowMeetingOccurrence, powwowMeetingId,
				orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PowwowMeetingOccurrence getByPowwowMeetingId_PrevAndNext(
		Session session, PowwowMeetingOccurrence powwowMeetingOccurrence,
		long powwowMeetingId,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE);

		query.append(_FINDER_COLUMN_POWWOWMEETINGID_POWWOWMEETINGID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PowwowMeetingOccurrenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(powwowMeetingId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						powwowMeetingOccurrence)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<PowwowMeetingOccurrence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the powwow meeting occurrences where powwowMeetingId = &#63; from the database.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 */
	@Override
	public void removeByPowwowMeetingId(long powwowMeetingId) {
		for (PowwowMeetingOccurrence powwowMeetingOccurrence :
				findByPowwowMeetingId(
					powwowMeetingId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(powwowMeetingOccurrence);
		}
	}

	/**
	 * Returns the number of powwow meeting occurrences where powwowMeetingId = &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @return the number of matching powwow meeting occurrences
	 */
	@Override
	public int countByPowwowMeetingId(long powwowMeetingId) {
		FinderPath finderPath = _finderPathCountByPowwowMeetingId;

		Object[] finderArgs = new Object[] {powwowMeetingId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_POWWOWMEETINGOCCURRENCE_WHERE);

			query.append(_FINDER_COLUMN_POWWOWMEETINGID_POWWOWMEETINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(powwowMeetingId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_POWWOWMEETINGID_POWWOWMEETINGID_2 =
			"powwowMeetingOccurrence.powwowMeetingId = ?";

	private FinderPath _finderPathWithPaginationFindByOS_ET;
	private FinderPath _finderPathWithPaginationCountByOS_ET;

	/**
	 * Returns all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime) {

		return findByOS_ET(
			occurrenceStatus, endTime, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end) {

		return findByOS_ET(occurrenceStatus, endTime, start, end, null);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return findByOS_ET(
			occurrenceStatus, endTime, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByOS_ET(
		String occurrenceStatus, long endTime, int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache) {

		occurrenceStatus = Objects.toString(occurrenceStatus, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByOS_ET;
		finderArgs = new Object[] {
			occurrenceStatus, endTime, start, end, orderByComparator
		};

		List<PowwowMeetingOccurrence> list = null;

		if (retrieveFromCache) {
			list = (List<PowwowMeetingOccurrence>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PowwowMeetingOccurrence powwowMeetingOccurrence : list) {
					if (!occurrenceStatus.equals(
							powwowMeetingOccurrence.getOccurrenceStatus()) ||
						(endTime <= powwowMeetingOccurrence.getEndTime())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE);

			boolean bindOccurrenceStatus = false;

			if (occurrenceStatus.isEmpty()) {
				query.append(_FINDER_COLUMN_OS_ET_OCCURRENCESTATUS_3);
			}
			else {
				bindOccurrenceStatus = true;

				query.append(_FINDER_COLUMN_OS_ET_OCCURRENCESTATUS_2);
			}

			query.append(_FINDER_COLUMN_OS_ET_ENDTIME_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(PowwowMeetingOccurrenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOccurrenceStatus) {
					qPos.add(occurrenceStatus);
				}

				qPos.add(endTime);

				if (!pagination) {
					list = (List<PowwowMeetingOccurrence>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PowwowMeetingOccurrence>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence findByOS_ET_First(
			String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		PowwowMeetingOccurrence powwowMeetingOccurrence = fetchByOS_ET_First(
			occurrenceStatus, endTime, orderByComparator);

		if (powwowMeetingOccurrence != null) {
			return powwowMeetingOccurrence;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("occurrenceStatus=");
		msg.append(occurrenceStatus);

		msg.append(", endTime=");
		msg.append(endTime);

		msg.append("}");

		throw new NoSuchMeetingOccurrenceException(msg.toString());
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence fetchByOS_ET_First(
		String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		List<PowwowMeetingOccurrence> list = findByOS_ET(
			occurrenceStatus, endTime, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence findByOS_ET_Last(
			String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		PowwowMeetingOccurrence powwowMeetingOccurrence = fetchByOS_ET_Last(
			occurrenceStatus, endTime, orderByComparator);

		if (powwowMeetingOccurrence != null) {
			return powwowMeetingOccurrence;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("occurrenceStatus=");
		msg.append(occurrenceStatus);

		msg.append(", endTime=");
		msg.append(endTime);

		msg.append("}");

		throw new NoSuchMeetingOccurrenceException(msg.toString());
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence fetchByOS_ET_Last(
		String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		int count = countByOS_ET(occurrenceStatus, endTime);

		if (count == 0) {
			return null;
		}

		List<PowwowMeetingOccurrence> list = findByOS_ET(
			occurrenceStatus, endTime, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the powwow meeting occurrences before and after the current powwow meeting occurrence in the ordered set where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceId the primary key of the current powwow meeting occurrence
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence[] findByOS_ET_PrevAndNext(
			long occurrenceId, String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		occurrenceStatus = Objects.toString(occurrenceStatus, "");

		PowwowMeetingOccurrence powwowMeetingOccurrence = findByPrimaryKey(
			occurrenceId);

		Session session = null;

		try {
			session = openSession();

			PowwowMeetingOccurrence[] array =
				new PowwowMeetingOccurrenceImpl[3];

			array[0] = getByOS_ET_PrevAndNext(
				session, powwowMeetingOccurrence, occurrenceStatus, endTime,
				orderByComparator, true);

			array[1] = powwowMeetingOccurrence;

			array[2] = getByOS_ET_PrevAndNext(
				session, powwowMeetingOccurrence, occurrenceStatus, endTime,
				orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PowwowMeetingOccurrence getByOS_ET_PrevAndNext(
		Session session, PowwowMeetingOccurrence powwowMeetingOccurrence,
		String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE);

		boolean bindOccurrenceStatus = false;

		if (occurrenceStatus.isEmpty()) {
			query.append(_FINDER_COLUMN_OS_ET_OCCURRENCESTATUS_3);
		}
		else {
			bindOccurrenceStatus = true;

			query.append(_FINDER_COLUMN_OS_ET_OCCURRENCESTATUS_2);
		}

		query.append(_FINDER_COLUMN_OS_ET_ENDTIME_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PowwowMeetingOccurrenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindOccurrenceStatus) {
			qPos.add(occurrenceStatus);
		}

		qPos.add(endTime);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						powwowMeetingOccurrence)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<PowwowMeetingOccurrence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63; from the database.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 */
	@Override
	public void removeByOS_ET(String occurrenceStatus, long endTime) {
		for (PowwowMeetingOccurrence powwowMeetingOccurrence :
				findByOS_ET(
					occurrenceStatus, endTime, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(powwowMeetingOccurrence);
		}
	}

	/**
	 * Returns the number of powwow meeting occurrences where occurrenceStatus = &#63; and endTime &lt; &#63;.
	 *
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the number of matching powwow meeting occurrences
	 */
	@Override
	public int countByOS_ET(String occurrenceStatus, long endTime) {
		occurrenceStatus = Objects.toString(occurrenceStatus, "");

		FinderPath finderPath = _finderPathWithPaginationCountByOS_ET;

		Object[] finderArgs = new Object[] {occurrenceStatus, endTime};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_POWWOWMEETINGOCCURRENCE_WHERE);

			boolean bindOccurrenceStatus = false;

			if (occurrenceStatus.isEmpty()) {
				query.append(_FINDER_COLUMN_OS_ET_OCCURRENCESTATUS_3);
			}
			else {
				bindOccurrenceStatus = true;

				query.append(_FINDER_COLUMN_OS_ET_OCCURRENCESTATUS_2);
			}

			query.append(_FINDER_COLUMN_OS_ET_ENDTIME_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOccurrenceStatus) {
					qPos.add(occurrenceStatus);
				}

				qPos.add(endTime);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_OS_ET_OCCURRENCESTATUS_2 =
		"powwowMeetingOccurrence.occurrenceStatus = ? AND ";

	private static final String _FINDER_COLUMN_OS_ET_OCCURRENCESTATUS_3 =
		"(powwowMeetingOccurrence.occurrenceStatus IS NULL OR powwowMeetingOccurrence.occurrenceStatus = '') AND ";

	private static final String _FINDER_COLUMN_OS_ET_ENDTIME_2 =
		"powwowMeetingOccurrence.endTime < ?";

	private FinderPath _finderPathWithPaginationFindByPMI_OS_ET;
	private FinderPath _finderPathWithPaginationCountByPMI_OS_ET;

	/**
	 * Returns all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime) {

		return findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end) {

		return findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime, start, end, null);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end, OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime, int start,
		int end, OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache) {

		occurrenceStatus = Objects.toString(occurrenceStatus, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByPMI_OS_ET;
		finderArgs = new Object[] {
			powwowMeetingId, occurrenceStatus, endTime, start, end,
			orderByComparator
		};

		List<PowwowMeetingOccurrence> list = null;

		if (retrieveFromCache) {
			list = (List<PowwowMeetingOccurrence>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PowwowMeetingOccurrence powwowMeetingOccurrence : list) {
					if ((powwowMeetingId !=
							powwowMeetingOccurrence.getPowwowMeetingId()) ||
						!occurrenceStatus.equals(
							powwowMeetingOccurrence.getOccurrenceStatus()) ||
						(endTime > powwowMeetingOccurrence.getEndTime())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE);

			query.append(_FINDER_COLUMN_PMI_OS_ET_POWWOWMEETINGID_2);

			boolean bindOccurrenceStatus = false;

			if (occurrenceStatus.isEmpty()) {
				query.append(_FINDER_COLUMN_PMI_OS_ET_OCCURRENCESTATUS_3);
			}
			else {
				bindOccurrenceStatus = true;

				query.append(_FINDER_COLUMN_PMI_OS_ET_OCCURRENCESTATUS_2);
			}

			query.append(_FINDER_COLUMN_PMI_OS_ET_ENDTIME_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(PowwowMeetingOccurrenceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(powwowMeetingId);

				if (bindOccurrenceStatus) {
					qPos.add(occurrenceStatus);
				}

				qPos.add(endTime);

				if (!pagination) {
					list = (List<PowwowMeetingOccurrence>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PowwowMeetingOccurrence>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence findByPMI_OS_ET_First(
			long powwowMeetingId, String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		PowwowMeetingOccurrence powwowMeetingOccurrence =
			fetchByPMI_OS_ET_First(
				powwowMeetingId, occurrenceStatus, endTime, orderByComparator);

		if (powwowMeetingOccurrence != null) {
			return powwowMeetingOccurrence;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("powwowMeetingId=");
		msg.append(powwowMeetingId);

		msg.append(", occurrenceStatus=");
		msg.append(occurrenceStatus);

		msg.append(", endTime=");
		msg.append(endTime);

		msg.append("}");

		throw new NoSuchMeetingOccurrenceException(msg.toString());
	}

	/**
	 * Returns the first powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence fetchByPMI_OS_ET_First(
		long powwowMeetingId, String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		List<PowwowMeetingOccurrence> list = findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence findByPMI_OS_ET_Last(
			long powwowMeetingId, String occurrenceStatus, long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		PowwowMeetingOccurrence powwowMeetingOccurrence = fetchByPMI_OS_ET_Last(
			powwowMeetingId, occurrenceStatus, endTime, orderByComparator);

		if (powwowMeetingOccurrence != null) {
			return powwowMeetingOccurrence;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("powwowMeetingId=");
		msg.append(powwowMeetingId);

		msg.append(", occurrenceStatus=");
		msg.append(occurrenceStatus);

		msg.append(", endTime=");
		msg.append(endTime);

		msg.append("}");

		throw new NoSuchMeetingOccurrenceException(msg.toString());
	}

	/**
	 * Returns the last powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching powwow meeting occurrence, or <code>null</code> if a matching powwow meeting occurrence could not be found
	 */
	@Override
	public PowwowMeetingOccurrence fetchByPMI_OS_ET_Last(
		long powwowMeetingId, String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		int count = countByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime);

		if (count == 0) {
			return null;
		}

		List<PowwowMeetingOccurrence> list = findByPMI_OS_ET(
			powwowMeetingId, occurrenceStatus, endTime, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the powwow meeting occurrences before and after the current powwow meeting occurrence in the ordered set where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param occurrenceId the primary key of the current powwow meeting occurrence
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence[] findByPMI_OS_ET_PrevAndNext(
			long occurrenceId, long powwowMeetingId, String occurrenceStatus,
			long endTime,
			OrderByComparator<PowwowMeetingOccurrence> orderByComparator)
		throws NoSuchMeetingOccurrenceException {

		occurrenceStatus = Objects.toString(occurrenceStatus, "");

		PowwowMeetingOccurrence powwowMeetingOccurrence = findByPrimaryKey(
			occurrenceId);

		Session session = null;

		try {
			session = openSession();

			PowwowMeetingOccurrence[] array =
				new PowwowMeetingOccurrenceImpl[3];

			array[0] = getByPMI_OS_ET_PrevAndNext(
				session, powwowMeetingOccurrence, powwowMeetingId,
				occurrenceStatus, endTime, orderByComparator, true);

			array[1] = powwowMeetingOccurrence;

			array[2] = getByPMI_OS_ET_PrevAndNext(
				session, powwowMeetingOccurrence, powwowMeetingId,
				occurrenceStatus, endTime, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PowwowMeetingOccurrence getByPMI_OS_ET_PrevAndNext(
		Session session, PowwowMeetingOccurrence powwowMeetingOccurrence,
		long powwowMeetingId, String occurrenceStatus, long endTime,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE);

		query.append(_FINDER_COLUMN_PMI_OS_ET_POWWOWMEETINGID_2);

		boolean bindOccurrenceStatus = false;

		if (occurrenceStatus.isEmpty()) {
			query.append(_FINDER_COLUMN_PMI_OS_ET_OCCURRENCESTATUS_3);
		}
		else {
			bindOccurrenceStatus = true;

			query.append(_FINDER_COLUMN_PMI_OS_ET_OCCURRENCESTATUS_2);
		}

		query.append(_FINDER_COLUMN_PMI_OS_ET_ENDTIME_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PowwowMeetingOccurrenceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(powwowMeetingId);

		if (bindOccurrenceStatus) {
			qPos.add(occurrenceStatus);
		}

		qPos.add(endTime);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						powwowMeetingOccurrence)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<PowwowMeetingOccurrence> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63; from the database.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 */
	@Override
	public void removeByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime) {

		for (PowwowMeetingOccurrence powwowMeetingOccurrence :
				findByPMI_OS_ET(
					powwowMeetingId, occurrenceStatus, endTime,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(powwowMeetingOccurrence);
		}
	}

	/**
	 * Returns the number of powwow meeting occurrences where powwowMeetingId = &#63; and occurrenceStatus = &#63; and endTime &ge; &#63;.
	 *
	 * @param powwowMeetingId the powwow meeting ID
	 * @param occurrenceStatus the occurrence status
	 * @param endTime the end time
	 * @return the number of matching powwow meeting occurrences
	 */
	@Override
	public int countByPMI_OS_ET(
		long powwowMeetingId, String occurrenceStatus, long endTime) {

		occurrenceStatus = Objects.toString(occurrenceStatus, "");

		FinderPath finderPath = _finderPathWithPaginationCountByPMI_OS_ET;

		Object[] finderArgs = new Object[] {
			powwowMeetingId, occurrenceStatus, endTime
		};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_POWWOWMEETINGOCCURRENCE_WHERE);

			query.append(_FINDER_COLUMN_PMI_OS_ET_POWWOWMEETINGID_2);

			boolean bindOccurrenceStatus = false;

			if (occurrenceStatus.isEmpty()) {
				query.append(_FINDER_COLUMN_PMI_OS_ET_OCCURRENCESTATUS_3);
			}
			else {
				bindOccurrenceStatus = true;

				query.append(_FINDER_COLUMN_PMI_OS_ET_OCCURRENCESTATUS_2);
			}

			query.append(_FINDER_COLUMN_PMI_OS_ET_ENDTIME_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(powwowMeetingId);

				if (bindOccurrenceStatus) {
					qPos.add(occurrenceStatus);
				}

				qPos.add(endTime);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PMI_OS_ET_POWWOWMEETINGID_2 =
		"powwowMeetingOccurrence.powwowMeetingId = ? AND ";

	private static final String _FINDER_COLUMN_PMI_OS_ET_OCCURRENCESTATUS_2 =
		"powwowMeetingOccurrence.occurrenceStatus = ? AND ";

	private static final String _FINDER_COLUMN_PMI_OS_ET_OCCURRENCESTATUS_3 =
		"(powwowMeetingOccurrence.occurrenceStatus IS NULL OR powwowMeetingOccurrence.occurrenceStatus = '') AND ";

	private static final String _FINDER_COLUMN_PMI_OS_ET_ENDTIME_2 =
		"powwowMeetingOccurrence.endTime >= ?";

	public PowwowMeetingOccurrencePersistenceImpl() {
		setModelClass(PowwowMeetingOccurrence.class);
	}

	/**
	 * Caches the powwow meeting occurrence in the entity cache if it is enabled.
	 *
	 * @param powwowMeetingOccurrence the powwow meeting occurrence
	 */
	@Override
	public void cacheResult(PowwowMeetingOccurrence powwowMeetingOccurrence) {
		EntityCacheUtil.putResult(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			powwowMeetingOccurrence.getPrimaryKey(), powwowMeetingOccurrence);

		powwowMeetingOccurrence.resetOriginalValues();
	}

	/**
	 * Caches the powwow meeting occurrences in the entity cache if it is enabled.
	 *
	 * @param powwowMeetingOccurrences the powwow meeting occurrences
	 */
	@Override
	public void cacheResult(
		List<PowwowMeetingOccurrence> powwowMeetingOccurrences) {

		for (PowwowMeetingOccurrence powwowMeetingOccurrence :
				powwowMeetingOccurrences) {

			if (EntityCacheUtil.getResult(
					PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
					PowwowMeetingOccurrenceImpl.class,
					powwowMeetingOccurrence.getPrimaryKey()) == null) {

				cacheResult(powwowMeetingOccurrence);
			}
			else {
				powwowMeetingOccurrence.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all powwow meeting occurrences.
	 *
	 * <p>
	 * The <code>com.liferay.portal.kernel.dao.orm.EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(PowwowMeetingOccurrenceImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the powwow meeting occurrence.
	 *
	 * <p>
	 * The <code>com.liferay.portal.kernel.dao.orm.EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PowwowMeetingOccurrence powwowMeetingOccurrence) {
		EntityCacheUtil.removeResult(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			powwowMeetingOccurrence.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<PowwowMeetingOccurrence> powwowMeetingOccurrences) {

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PowwowMeetingOccurrence powwowMeetingOccurrence :
				powwowMeetingOccurrences) {

			EntityCacheUtil.removeResult(
				PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
				PowwowMeetingOccurrenceImpl.class,
				powwowMeetingOccurrence.getPrimaryKey());
		}
	}

	/**
	 * Creates a new powwow meeting occurrence with the primary key. Does not add the powwow meeting occurrence to the database.
	 *
	 * @param occurrenceId the primary key for the new powwow meeting occurrence
	 * @return the new powwow meeting occurrence
	 */
	@Override
	public PowwowMeetingOccurrence create(long occurrenceId) {
		PowwowMeetingOccurrence powwowMeetingOccurrence =
			new PowwowMeetingOccurrenceImpl();

		powwowMeetingOccurrence.setNew(true);
		powwowMeetingOccurrence.setPrimaryKey(occurrenceId);

		powwowMeetingOccurrence.setCompanyId(CompanyThreadLocal.getCompanyId());

		return powwowMeetingOccurrence;
	}

	/**
	 * Removes the powwow meeting occurrence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence that was removed
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence remove(long occurrenceId)
		throws NoSuchMeetingOccurrenceException {

		return remove((Serializable)occurrenceId);
	}

	/**
	 * Removes the powwow meeting occurrence with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence that was removed
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence remove(Serializable primaryKey)
		throws NoSuchMeetingOccurrenceException {

		Session session = null;

		try {
			session = openSession();

			PowwowMeetingOccurrence powwowMeetingOccurrence =
				(PowwowMeetingOccurrence)session.get(
					PowwowMeetingOccurrenceImpl.class, primaryKey);

			if (powwowMeetingOccurrence == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMeetingOccurrenceException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(powwowMeetingOccurrence);
		}
		catch (NoSuchMeetingOccurrenceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected PowwowMeetingOccurrence removeImpl(
		PowwowMeetingOccurrence powwowMeetingOccurrence) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(powwowMeetingOccurrence)) {
				powwowMeetingOccurrence = (PowwowMeetingOccurrence)session.get(
					PowwowMeetingOccurrenceImpl.class,
					powwowMeetingOccurrence.getPrimaryKeyObj());
			}

			if (powwowMeetingOccurrence != null) {
				session.delete(powwowMeetingOccurrence);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (powwowMeetingOccurrence != null) {
			clearCache(powwowMeetingOccurrence);
		}

		return powwowMeetingOccurrence;
	}

	@Override
	public PowwowMeetingOccurrence updateImpl(
		PowwowMeetingOccurrence powwowMeetingOccurrence) {

		boolean isNew = powwowMeetingOccurrence.isNew();

		if (!(powwowMeetingOccurrence instanceof
				PowwowMeetingOccurrenceModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(powwowMeetingOccurrence.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					powwowMeetingOccurrence);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in powwowMeetingOccurrence proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom PowwowMeetingOccurrence implementation " +
					powwowMeetingOccurrence.getClass());
		}

		PowwowMeetingOccurrenceModelImpl powwowMeetingOccurrenceModelImpl =
			(PowwowMeetingOccurrenceModelImpl)powwowMeetingOccurrence;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (powwowMeetingOccurrence.getCreateDate() == null)) {
			if (serviceContext == null) {
				powwowMeetingOccurrence.setCreateDate(now);
			}
			else {
				powwowMeetingOccurrence.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!powwowMeetingOccurrenceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				powwowMeetingOccurrence.setModifiedDate(now);
			}
			else {
				powwowMeetingOccurrence.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (powwowMeetingOccurrence.isNew()) {
				session.save(powwowMeetingOccurrence);

				powwowMeetingOccurrence.setNew(false);
			}
			else {
				powwowMeetingOccurrence =
					(PowwowMeetingOccurrence)session.merge(
						powwowMeetingOccurrence);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PowwowMeetingOccurrenceModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				powwowMeetingOccurrenceModelImpl.getPowwowMeetingId()
			};

			FinderCacheUtil.removeResult(
				_finderPathCountByPowwowMeetingId, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByPowwowMeetingId, args);

			FinderCacheUtil.removeResult(
				_finderPathCountAll, FINDER_ARGS_EMPTY);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((powwowMeetingOccurrenceModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPowwowMeetingId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					powwowMeetingOccurrenceModelImpl.
						getOriginalPowwowMeetingId()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByPowwowMeetingId, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByPowwowMeetingId, args);

				args = new Object[] {
					powwowMeetingOccurrenceModelImpl.getPowwowMeetingId()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByPowwowMeetingId, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByPowwowMeetingId, args);
			}
		}

		EntityCacheUtil.putResult(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			powwowMeetingOccurrence.getPrimaryKey(), powwowMeetingOccurrence,
			false);

		powwowMeetingOccurrence.resetOriginalValues();

		return powwowMeetingOccurrence;
	}

	/**
	 * Returns the powwow meeting occurrence with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMeetingOccurrenceException {

		PowwowMeetingOccurrence powwowMeetingOccurrence = fetchByPrimaryKey(
			primaryKey);

		if (powwowMeetingOccurrence == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMeetingOccurrenceException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return powwowMeetingOccurrence;
	}

	/**
	 * Returns the powwow meeting occurrence with the primary key or throws a <code>NoSuchMeetingOccurrenceException</code> if it could not be found.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence
	 * @throws NoSuchMeetingOccurrenceException if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence findByPrimaryKey(long occurrenceId)
		throws NoSuchMeetingOccurrenceException {

		return findByPrimaryKey((Serializable)occurrenceId);
	}

	/**
	 * Returns the powwow meeting occurrence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence, or <code>null</code> if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = EntityCacheUtil.getResult(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PowwowMeetingOccurrence powwowMeetingOccurrence =
			(PowwowMeetingOccurrence)serializable;

		if (powwowMeetingOccurrence == null) {
			Session session = null;

			try {
				session = openSession();

				powwowMeetingOccurrence = (PowwowMeetingOccurrence)session.get(
					PowwowMeetingOccurrenceImpl.class, primaryKey);

				if (powwowMeetingOccurrence != null) {
					cacheResult(powwowMeetingOccurrence);
				}
				else {
					EntityCacheUtil.putResult(
						PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
						PowwowMeetingOccurrenceImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(
					PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
					PowwowMeetingOccurrenceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return powwowMeetingOccurrence;
	}

	/**
	 * Returns the powwow meeting occurrence with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param occurrenceId the primary key of the powwow meeting occurrence
	 * @return the powwow meeting occurrence, or <code>null</code> if a powwow meeting occurrence with the primary key could not be found
	 */
	@Override
	public PowwowMeetingOccurrence fetchByPrimaryKey(long occurrenceId) {
		return fetchByPrimaryKey((Serializable)occurrenceId);
	}

	@Override
	public Map<Serializable, PowwowMeetingOccurrence> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PowwowMeetingOccurrence> map =
			new HashMap<Serializable, PowwowMeetingOccurrence>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PowwowMeetingOccurrence powwowMeetingOccurrence = fetchByPrimaryKey(
				primaryKey);

			if (powwowMeetingOccurrence != null) {
				map.put(primaryKey, powwowMeetingOccurrence);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = EntityCacheUtil.getResult(
				PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
				PowwowMeetingOccurrenceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PowwowMeetingOccurrence)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (PowwowMeetingOccurrence powwowMeetingOccurrence :
					(List<PowwowMeetingOccurrence>)q.list()) {

				map.put(
					powwowMeetingOccurrence.getPrimaryKeyObj(),
					powwowMeetingOccurrence);

				cacheResult(powwowMeetingOccurrence);

				uncachedPrimaryKeys.remove(
					powwowMeetingOccurrence.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(
					PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
					PowwowMeetingOccurrenceImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the powwow meeting occurrences.
	 *
	 * @return the powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the powwow meeting occurrences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @return the range of powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findAll(
		int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the powwow meeting occurrences.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PowwowMeetingOccurrenceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of powwow meeting occurrences
	 * @param end the upper bound of the range of powwow meeting occurrences (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of powwow meeting occurrences
	 */
	@Override
	public List<PowwowMeetingOccurrence> findAll(
		int start, int end,
		OrderByComparator<PowwowMeetingOccurrence> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<PowwowMeetingOccurrence> list = null;

		if (retrieveFromCache) {
			list = (List<PowwowMeetingOccurrence>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_POWWOWMEETINGOCCURRENCE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_POWWOWMEETINGOCCURRENCE;

				if (pagination) {
					sql = sql.concat(
						PowwowMeetingOccurrenceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PowwowMeetingOccurrence>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PowwowMeetingOccurrence>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the powwow meeting occurrences from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PowwowMeetingOccurrence powwowMeetingOccurrence : findAll()) {
			remove(powwowMeetingOccurrence);
		}
	}

	/**
	 * Returns the number of powwow meeting occurrences.
	 *
	 * @return the number of powwow meeting occurrences
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
					_SQL_COUNT_POWWOWMEETINGOCCURRENCE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PowwowMeetingOccurrenceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the powwow meeting occurrence persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByPowwowMeetingId = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPowwowMeetingId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPowwowMeetingId = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPowwowMeetingId",
			new String[] {Long.class.getName()},
			PowwowMeetingOccurrenceModelImpl.POWWOWMEETINGID_COLUMN_BITMASK |
			PowwowMeetingOccurrenceModelImpl.STARTTIME_COLUMN_BITMASK);

		_finderPathCountByPowwowMeetingId = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPowwowMeetingId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByOS_ET = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOS_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByOS_ET = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOS_ET",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByPMI_OS_ET = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED,
			PowwowMeetingOccurrenceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPMI_OS_ET",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByPMI_OS_ET = new FinderPath(
			PowwowMeetingOccurrenceModelImpl.ENTITY_CACHE_ENABLED,
			PowwowMeetingOccurrenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPMI_OS_ET",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName()
			});
	}

	public void destroy() {
		EntityCacheUtil.removeCache(
			PowwowMeetingOccurrenceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_POWWOWMEETINGOCCURRENCE =
		"SELECT powwowMeetingOccurrence FROM PowwowMeetingOccurrence powwowMeetingOccurrence";

	private static final String
		_SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE_PKS_IN =
			"SELECT powwowMeetingOccurrence FROM PowwowMeetingOccurrence powwowMeetingOccurrence WHERE occurrenceId IN (";

	private static final String _SQL_SELECT_POWWOWMEETINGOCCURRENCE_WHERE =
		"SELECT powwowMeetingOccurrence FROM PowwowMeetingOccurrence powwowMeetingOccurrence WHERE ";

	private static final String _SQL_COUNT_POWWOWMEETINGOCCURRENCE =
		"SELECT COUNT(powwowMeetingOccurrence) FROM PowwowMeetingOccurrence powwowMeetingOccurrence";

	private static final String _SQL_COUNT_POWWOWMEETINGOCCURRENCE_WHERE =
		"SELECT COUNT(powwowMeetingOccurrence) FROM PowwowMeetingOccurrence powwowMeetingOccurrence WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"powwowMeetingOccurrence.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No PowwowMeetingOccurrence exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No PowwowMeetingOccurrence exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		PowwowMeetingOccurrencePersistenceImpl.class);

}