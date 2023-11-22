import { createI18n } from 'vue-i18n';
import frMessages from './fr';

const i18n = createI18n({
    legacy: false,
    locale: 'fr',
    fallbackLocale: 'fr',
    messages: {
        fr: frMessages
    },
    // datetimeFormats,
    // numberFormats
});

export default i18n;
