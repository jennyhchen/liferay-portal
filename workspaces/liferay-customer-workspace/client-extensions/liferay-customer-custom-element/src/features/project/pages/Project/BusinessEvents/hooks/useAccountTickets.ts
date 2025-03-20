/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useCallback, useEffect, useState} from 'react';
import {Liferay} from '~/services/liferay';

export interface ITicket {
	link: string;
	status: string;
	subject: string
	ticketId: number;
}

const useAccountTickets = (externalReferenceCode: string) => {
	const [ticketError, setTicketError] = useState(false);
	const [tickets, setTickets] = useState<ITicket[] | undefined>(
		undefined
	);

	const fetchTickets = useCallback(async () => {
		if (!externalReferenceCode) {
			setTicketError(false);
			setTickets(undefined);

			return;
		}

		try {
			const response: ITicket[] =
				await Liferay.OAuth2Client.FromUserAgentApplication(
					'liferay-customer-etc-spring-boot-oaua'
				)
					.fetch(`/accounts/${externalReferenceCode}/tickets`)
					.then((response: {json: () => any}) => response.json());

			setTicketError(false);
			setTickets(response);
		}
		catch (error) {
			console.error('Error fetching tickets data:', error);

			setTicketError(true);
			setTickets(undefined);
		}
	}, [externalReferenceCode]);

	useEffect(() => {
		fetchTickets();
	}, [fetchTickets]);

	return {ticketError, tickets};
};

export default useAccountTickets;
