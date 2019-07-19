(function() {
	var STR_BLANK = '';

	var STR_COMMA = ',';

	var STR_DASH = '-';

	var STR_DOT = '.';

	var STR_PLUS = '+';

	var STR_SPACE = ' ';

	var TPL_SPAN = '<span>';

	var TPL_SPAN_CLOSE = '</span>';

	AUI.add(
		'liferay-meeting-calendar-recurrence-util',
		function(A) {
			Liferay.MeetingRecurrenceUtil = {
				FREQUENCY: {
					DAILY: 'DAILY',
					MONTHLY: 'MONTHLY',
					WEEKLY: 'WEEKLY'
				},

				INTERVAL_LABELS: {
					DAILY: Liferay.Language.get('days'),
					MONTHLY: Liferay.Language.get('months'),
					WEEKLY: Liferay.Language.get('weeks')
				},

				MONTH_LABELS: [
					Liferay.Language.get('january'),
					Liferay.Language.get('february'),
					Liferay.Language.get('march'),
					Liferay.Language.get('april'),
					Liferay.Language.get('may'),
					Liferay.Language.get('june'),
					Liferay.Language.get('july'),
					Liferay.Language.get('august'),
					Liferay.Language.get('september'),
					Liferay.Language.get('october'),
					Liferay.Language.get('november'),
					Liferay.Language.get('december')
				],

				POSITION_LABELS: {
					'-1': Liferay.Language.get('last'),
					'1': Liferay.Language.get('first'),
					'2': Liferay.Language.get('second'),
					'3': Liferay.Language.get('third'),
					'4': Liferay.Language.get('fourth')
				},

				WEEKDAY_LABELS: {
					FR: Liferay.Language.get('weekday.FR'),
					MO: Liferay.Language.get('weekday.MO'),
					SA: Liferay.Language.get('weekday.SA'),
					SU: Liferay.Language.get('weekday.SU'),
					TH: Liferay.Language.get('weekday.TH'),
					TU: Liferay.Language.get('weekday.TU'),
					WE: Liferay.Language.get('weekday.WE')
				},

				getSummary: function(recurrence) {
					var instance = this;

					var month = null;
					var position = null;
					var template = [];
					var weekDay = null;
					var weekDays = null;

					if (recurrence.interval == 1) {
						template.push(A.Lang.String.capitalize(recurrence.frequency));
					}
					else {
						template.push(Liferay.Language.get('every'), ' {interval} {intervalLabel}');
					}

					if (recurrence.positionalWeekday) {
						if (recurrence.frequency == instance.FREQUENCY.MONTHLY) {
							template.push(STR_SPACE, Liferay.Language.get('on'), ' {position} {weekDay}');
						}
						else {
							template.push(STR_SPACE, Liferay.Language.get('on-the'), ' {position} {weekDay} ', Liferay.Language.get('of'), ' {month}');
						}

						month = instance.MONTH_LABELS[recurrence.positionalWeekday.month];
						position = instance.POSITION_LABELS[recurrence.positionalWeekday.position];
						weekDay = instance.WEEKDAY_LABELS[recurrence.positionalWeekday.weekday];
					}
					else if ((recurrence.frequency == instance.FREQUENCY.WEEKLY) && (recurrence.weekdays.length > 0)) {
						template.push(STR_SPACE, TPL_SPAN, Liferay.Language.get('on'), TPL_SPAN_CLOSE, ' {weekDays}');

						weekDays = recurrence.weekdays.map(
							function(item) {
								return instance.WEEKDAY_LABELS[item];
							}
						);
					}

					if (recurrence.count && (recurrence.endValue === 'after')) {
						template.push(', {count} ', Liferay.Language.get('times'));
					}
					else if (recurrence.untilDate && (recurrence.endValue === 'on')) {
						var untilDate = recurrence.untilDate;

						template.push(
							STR_COMMA,
							STR_SPACE,
							TPL_SPAN,
							Liferay.Language.get('until'),
							TPL_SPAN_CLOSE,
							A.Lang.sub(
								' {month} {date}, {year}',
								{
									date: untilDate.getDate(),
									month: instance.MONTH_LABELS[untilDate.getMonth()],
									year: untilDate.getFullYear()
								}
							)
						);
					}

					var summary = A.Lang.sub(
						template.join(STR_BLANK),
						{
							count: recurrence.count,
							interval: recurrence.interval,
							intervalLabel: instance.INTERVAL_LABELS[recurrence.frequency],
							month: month,
							position: position,
							weekDay: weekDay,
							weekDays: weekDays
						}
					);

					return summary;
				},

				openConfirmationPanel: function(actionName, onlyThisInstanceFn, allFollowingFn, allEventsInFn, cancelFn) {
					var instance = this;

					var changeDeleteText;
					var confirmationPanel;
					var titleText;

					if (actionName === 'delete') {
						titleText = Liferay.Language.get('delete-recurring-event');
						changeDeleteText = Liferay.Language.get('would-you-like-to-delete-only-this-event,-all-events-in-the-series,-or-this-and-all-future-events-in-the-series');
					}
					else {
						titleText = Liferay.Language.get('change-recurring-event');
						changeDeleteText = Liferay.Language.get('would-you-like-to-change-only-this-event,-all-events-in-the-series,-or-this-and-all-future-events-in-the-series');
					}

					var getButtonConfig = function(label, callback) {
						return {
							label: label,
							on: {
								click: function() {
									if (callback) {
										callback.apply(this, arguments);
									}

									confirmationPanel.hide();
								}
							}
						};
					};

					confirmationPanel = Liferay.Util.Window.getWindow(
						{
							dialog:	{
								bodyContent: changeDeleteText,
								destroyOnHide: true,
								height: 250,
								hideOn: [],
								resizable: false,
								toolbars: {
									footer: [
										getButtonConfig(Liferay.Language.get('only-this-instance'), onlyThisInstanceFn),
										getButtonConfig(Liferay.Language.get('all-following'), allFollowingFn),
										getButtonConfig(Liferay.Language.get('all-events-in-the-series'), allEventsInFn),
										getButtonConfig(Liferay.Language.get('cancel-this-change'), cancelFn)
									]
								},
								width: 700
							},
							title: titleText
						}
					);

					return confirmationPanel.render().show();
				}
			};
		},
		'',
		{
			requires: ['aui-base', 'liferay-util-window']
		}
	);

	AUI.add(
		'liferay-meeting-calendar-recurrence-dialog',
		function(A) {
			var Lang = A.Lang;

			var DAYS_OF_WEEK = ['SU', 'MO', 'TU', 'WE', 'TH', 'FR', 'SA'];

			var FREQUENCY_DAILY = 'DAILY';

			var FREQUENCY_MONTHLY = 'MONTHLY';

			var FREQUENCY_WEEKLY = 'WEEKLY';

			var LIMIT_COUNT = 'after';

			var LIMIT_DATE = 'on';

			var TPL_INTERVAL_OPTION = '<option class="" value="{value}">{value}</option>';

			var TPL_INTERVAL_OPTION = '<option class="" value="{value}">{value}</option>';

			var WEEK_LENGTH = A.DataType.DateMath.WEEK_LENGTH;

			var MeetingRecurrenceDialogController = A.Component.create(
				{

					ATTRS: {
						container: {
							setter: A.one,
							value: null
						},

						dayOfWeekInput: {
							setter: A.one,
							value: null
						},

						daysOfWeek: {
							getter: '_getDaysOfWeek'
						},

						daysOfWeekCheckboxes: {
							getter: '_getDaysOfWeekCheckboxes'
						},

						frequency: {
							getter: '_getFrequency'
						},

						frequencySelect: {
							setter: A.one,
							value: null
						},

						recurrenceInterval: {
							setter: A.one,
							value: null
						},

						interval: {
							getter: '_getInterval'
						},

						intervalSelect: {
							setter: A.one,
							value: null
						},

						lastPositionCheckbox: {
							setter: A.one,
							value: null
						},

						limitCount: {
							getter: '_getLimitCount'
						},

						limitCountInput: {
							setter: A.one,
							value: null
						},

						limitCountRadioButton: {
							setter: A.one,
							value: null
						},

						limitDate: {
							getter: '_getLimitDate'
						},

						limitDateDatePicker: {
							value: null
						},

						limitDateRadioButton: {
							setter: A.one,
							value: null
						},

						limitRadioButtons: {
							getter: '_getLimitRadioButtons'
						},

						limitType: {
							getter: '_getLimitType'
						},

						monthlyRecurrenceOptions: {
							setter: A.one,
							value: null
						},

						position: {
							getter: '_getPosition'
						},

						positionalDayOfWeek: {
							getter: '_getPositionalDayOfWeek'
						},

						positionalDayOfWeekOptions: {
							setter: A.one,
							value: null
						},

						positionInput: {
							setter: A.one,
							value: null
						},

						positionSelect: {
							setter: A.one,
							value: null
						},

						recurrence: {
							getter: '_getRecurrence'
						},

						repeatCheckbox: {
							setter: A.one,
							value: null
						},

						repeatOnDayOfMonthRadioButton: {
							setter: A.one,
							value: null
						},

						repeatOnDayOfWeekRadioButton: {
							setter: A.one,
							value: null
						},

						startDate: {
							getter: '_getStartDate'
						},

						startDateDatePicker: {
							value: null
						},

						startDatePosition: {
							getter: '_getStartDatePosition'
						},

						startTimeDayOfWeekInput: {
							setter: A.one,
							value: null
						},

						summary: {
							getter: '_getSummary'
						},

						weeklyRecurrenceOptions: {
							setter: A.one,
							value: null
						}
					},

					NAME: 'recurrence-dialog',

					prototype: {
						initializer: function() {
							var instance = this;

							instance.bindUI();
							instance._bindIntervalSelection();
						},

						bindUI: function() {
							var instance = this;

							var container = instance.get('container');

							var limitDateDatePicker = instance.get('limitDateDatePicker');

							var startDateDatePicker = instance.get('startDateDatePicker');

							container.delegate('change', A.bind(instance._onInputChange, instance), 'select,input');
							container.delegate('keypress', A.bind(instance._onInputChange, instance), 'select');

							limitDateDatePicker.after('selectionChange', A.bind(instance._onInputChange, instance));

							startDateDatePicker.after('selectionChange', A.bind(instance._onStartDateDatePickerChange, instance));
						},

						_bindIntervalSelection: function() {
							var instance = this;

							var selectedFrequency = instance._getFrequency();

							var intervalSelect = instance.get('intervalSelect');
							intervalSelect.all('option').remove();

							var maxInterval = 0;

							if (selectedFrequency == FREQUENCY_DAILY) {
								maxInterval = 30;
							}
							else if (selectedFrequency == FREQUENCY_WEEKLY) {
								maxInterval = 12;
							}
							else if (selectedFrequency == FREQUENCY_MONTHLY) {
								maxInterval = 3;
							}

							if (intervalSelect) {
								for(var i = 1; i <= maxInterval; i ++) {
									var data = {
										value: i
									};
									intervalSelect.append(Lang.sub(TPL_INTERVAL_OPTION, data));
								}
							}

							instance._updateIntervalSelection(maxInterval);
						},

						_updateIntervalSelection: function(maxInterval) {
							var instance = this;
							var intervalSelect = instance.get('intervalSelect');
							var recurrenceInterval = instance.get("recurrenceInterval").val();

							if (recurrenceInterval > 0 && recurrenceInterval < maxInterval) {
								intervalSelect.val(recurrenceInterval);
							}
						},

						_calculatePosition: function() {
							var instance = this;

							var lastPositionCheckbox = instance.get('lastPositionCheckbox');

							var position = instance.get('startDatePosition');

							if (instance._isLastDayOfWeekInMonth()) {
								if ((position > 4) || lastPositionCheckbox.get('checked')) {
									position = -1;
								}
							}

							return position;
						},

						_canChooseLastDayOfWeek: function() {
							var instance = this;

							var mandatoryLastDay = instance.get('startDatePosition') > 4;

							return instance._isLastDayOfWeekInMonth() && !mandatoryLastDay;
						},

						_getDaysOfWeek: function() {
							var instance = this;

							var dayOfWeekNodes = instance.get('daysOfWeekCheckboxes').filter(':checked');

							return dayOfWeekNodes.val();
						},

						_getDaysOfWeekCheckboxes: function() {
							var instance = this;

							var weeklyRecurrenceOptions = instance.get('weeklyRecurrenceOptions');

							return weeklyRecurrenceOptions.all(':checkbox');
						},

						_getFrequency: function() {
							var instance = this;

							var frequencySelect = instance.get('frequencySelect');

							return frequencySelect.val();
						},

						_getInterval: function() {
							var instance = this;

							var intervalSelect = instance.get('intervalSelect');

							return intervalSelect.val();
						},

						_getLimitCount: function() {
							var instance = this;

							var limitCountInput = instance.get('limitCountInput');

							return parseInt(limitCountInput.val(), 10);
						},

						_getLimitDate: function() {
							var instance = this;

							var limitDateDatePicker = instance.get('limitDateDatePicker');

							return limitDateDatePicker.getDate();
						},

						_getLimitRadioButtons: function() {
							var instance = this;

							return [instance.get('limitCountRadioButton'), instance.get('limitDateRadioButton')];
						},

						_getLimitType: function() {
							var instance = this;

							var checkedLimitRadioButton = A.Array.find(
								instance.get('limitRadioButtons'),
								function(item, index) {
									return item.get('checked');
								}
							);

							return checkedLimitRadioButton && checkedLimitRadioButton.val();
						},

						_getPosition: function() {
							var instance = this;

							var positionInput = instance.get('positionInput');

							return positionInput.val();
						},

						_getPositionalDayOfWeek: function() {
							var instance = this;

							var dayOfWeekInput = instance.get('dayOfWeekInput');

							var positionalDayOfWeek = null;

							var repeatOnDayOfWeek = instance.get('repeatOnDayOfWeekRadioButton').get('checked');

							var startDate = instance.get('startDate');

							if (instance._isPositionalFrequency() && repeatOnDayOfWeek) {
								positionalDayOfWeek = {
									month: startDate.getMonth(),
									position: instance.get('position'),
									weekday: dayOfWeekInput.val()
								}
							}

							return positionalDayOfWeek;
						},

						_getRecurrence: function() {
							var instance = this;

							return {
								count: instance.get('limitCount'),
								endValue: instance.get('limitType'),
								frequency: instance.get('frequency'),
								interval: instance.get('interval'),
								positionalWeekday: instance.get('positionalDayOfWeek'),
								untilDate: instance.get('limitDate'),
								weekdays: instance.get('daysOfWeek')
							};
						},

						_getStartDate: function() {
							var instance = this;

							var startDateDatePicker = instance.get('startDateDatePicker');

							return startDateDatePicker.getDate();
						},

						_getStartDatePosition: function() {
							var instance = this;

							var startDateDatePicker = instance.get('startDateDatePicker');

							var startDate = startDateDatePicker.getDate();

							return Math.ceil(startDate.getDate() / WEEK_LENGTH);
						},

						_getSummary: function() {
							var instance = this;

							var recurrence = instance.get('recurrence');

							return Liferay.MeetingRecurrenceUtil.getSummary(recurrence);
						},

						_isLastDayOfWeekInMonth: function() {
							var instance = this;

							var startDate = instance.get('startDate');

							var lastDate = A.DataType.DateMath.findMonthEnd(startDate);

							return lastDate.getDate() - startDate.getDate() < WEEK_LENGTH;
						},

						_isPositionalFrequency: function() {
							var instance = this;

							var frequency = instance.get('frequency');

							return frequency === FREQUENCY_MONTHLY;
						},

						_onInputChange: function(event) {
							var instance = this;

							var currentTarget = event.currentTarget;

							var limitCountInput = instance.get('limitCountInput');
							var limitDateDatePicker = instance.get('limitDateDatePicker');
							var limitType = instance.get('limitType');

							if (currentTarget === instance.get('frequencySelect')) {
								instance._toggleView('weeklyRecurrenceOptions', instance.get('frequency') === FREQUENCY_WEEKLY);
								instance._toggleView('monthlyRecurrenceOptions', instance._isPositionalFrequency());

								instance._bindIntervalSelection();
							}

							if (currentTarget === instance.get('repeatOnDayOfMonthRadioButton')) {
								instance._toggleView('positionalDayOfWeekOptions', !currentTarget.get('checked'));
							}
							else if (currentTarget === instance.get('repeatOnDayOfWeekRadioButton')) {
								instance._toggleView('positionalDayOfWeekOptions', currentTarget.get('checked') && instance._canChooseLastDayOfWeek());
							}

							if (currentTarget === instance.get('lastPositionCheckbox')) {
								var positionInput = instance.get('positionInput');

								positionInput.val(instance._calculatePosition());
							}

							var disableLimitCountInput = limitType === LIMIT_DATE;

							Liferay.Util.toggleDisabled(limitCountInput, disableLimitCountInput);

							limitCountInput.selectText();

							var disableLimitDateDatePicker = limitType === LIMIT_COUNT;

							limitDateDatePicker.set('disabled', disableLimitDateDatePicker);

							instance.fire('recurrenceChange');
						},

						_onStartDateDatePickerChange: function(event) {
							var instance = this;

							var date = event.newSelection[0];

							var dayOfWeek = DAYS_OF_WEEK[date.getDay()];

							var dayOfWeekInput = instance.get('dayOfWeekInput');

							var daysOfWeekCheckboxes = instance.get('daysOfWeekCheckboxes');

							var positionInput = instance.get('positionInput');

							var repeatCheckbox = instance.get('repeatCheckbox');

							var repeatOnDayOfWeekRadioButton = instance.get('repeatOnDayOfWeekRadioButton');

							var startTimeDayOfWeekInput = instance.get('startTimeDayOfWeekInput');

							startTimeDayOfWeekInput.val(dayOfWeek);

							daysOfWeekCheckboxes.each(
								function(item) {
									if (item.val() == dayOfWeek) {
										item.set('checked', true);
										item.set('disabled', true);
									}
									else if (item.get('disabled')) {
										item.set('disabled', false);

										if (!repeatCheckbox.get('checked')) {
											item.set('checked', false);
										}
									}
								}
							);

							dayOfWeekInput.val(dayOfWeek);

							positionInput.val(instance._calculatePosition());

							if (repeatOnDayOfWeekRadioButton.get('checked')) {
								instance._toggleView('positionalDayOfWeekOptions', instance._canChooseLastDayOfWeek());
							}

							if (repeatCheckbox.get('checked')) {
								instance.fire('recurrenceChange');
							}
						},

						_toggleView: function(viewName, show) {
							var instance = this;

							var viewNode = instance.get(viewName);

							if (viewNode) {
								viewNode.toggle(show);
							}
						}
					}
				}
			);

			Liferay.MeetingRecurrenceDialogController = MeetingRecurrenceDialogController;
		},
		'',
		{
			requires: ['aui-base', 'aui-datatype', 'liferay-meeting-calendar-recurrence-util']
		}
	);
}());