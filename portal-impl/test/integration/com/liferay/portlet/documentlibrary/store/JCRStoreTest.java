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

import com.liferay.portal.jcr.JCRFactory;
import com.liferay.portal.jcr.JCRFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Preston Crary
 */
public class JCRStoreTest extends BaseStoreTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() throws Exception {
		JCRFactoryUtil.prepare();
		JCRFactoryUtil.initialize();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		JCRFactoryUtil.shutdown();
	}

	@Test
	public void testMove() throws Exception {
		String srcDir = RandomTestUtil.randomString();

		store.addDirectory(companyId, repositoryId, srcDir);

		Assert.assertTrue(store.hasDirectory(companyId, repositoryId, srcDir));

		String destDir = RandomTestUtil.randomString();

		store.move(getAbsolutePath(srcDir), getAbsolutePath(destDir));

		Assert.assertFalse(store.hasDirectory(companyId, repositoryId, srcDir));
		Assert.assertTrue(store.hasDirectory(companyId, repositoryId, destDir));
	}

	protected String getAbsolutePath(String directory) {
		StringBundler sb = new StringBundler(8);

		sb.append(StringPool.SLASH);
		sb.append(companyId);
		sb.append(StringPool.SLASH);
		sb.append(JCRFactory.NODE_DOCUMENTLIBRARY);
		sb.append(StringPool.SLASH);
		sb.append(repositoryId);
		sb.append(StringPool.SLASH);
		sb.append(directory);

		return sb.toString();
	}

	@Override
	protected Store getStore() {
		return new JCRStore();
	}

}