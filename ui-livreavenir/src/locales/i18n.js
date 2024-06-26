import { createI18n } from 'vue-i18n';
import frMessages from './fr';
import datetimeFormats from './datetimeFormats';

const i18n = createI18n({
	legacy: false,
	locale: 'fr',
	fallbackLocale: 'fr',
	messages: {
		fr: frMessages
	},
	datetimeFormats
});

export default i18n;
