/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ApolloClient, FetchResult} from '@apollo/client';
import {Button as ClayButton} from '@clayui/core';
import {ClayCheckbox, ClayInput} from '@clayui/form';
import {useCallback, useEffect, useState} from 'react';
import {Liferay} from '~/services/liferay';
import i18n from '~/utils/I18n';
import {getBusinessEvents} from '~/services/liferay/api';
import {IBusinessEvent} from '~/utils/types';

export interface IAssociatedTickets {
	ticketId: number
}

export interface IBusinessEvents {
	businessEvents: IBusinessEvent[],
}

const useAccountBusinessEvents = (externalReferenceCode: string, businessEvents: IBusinessEvent) => {
	console.log(businessEvents);

	const filterQuery = `filter='eventStatus' eq 'Open' and 'r_accountEntryToBusinessEvents_accountEntryERC' eq '${externalReferenceCode}'`;

	const businessEventResponse = getBusinessEvents(filterQuery);

	console.log(businessEventResponse);

	const [openBusinessEvents, setOpenBusinessEvents] = useState<IBusinessEvent[]>([]);

	const fetchOpenBusinessEvents = useCallback(() => {
		try {
			const openBusinessEventsResponse = getBusinessEvents(filterQuery);

			console.log(openBusinessEventsResponse.items);
			setOpenBusinessEvents(openBusinessEventsResponse.items);
		}
		catch (error) {
			console.error('Error getting open business events', error);
		}
	}, [filterQuery]);

	fetchOpenBusinessEvents();

	console.log(openBusinessEvents);
};

export default useAccountBusinessEvents;
