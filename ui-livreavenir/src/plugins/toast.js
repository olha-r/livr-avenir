function showToast(id, cls, message) {
    const container = document.querySelector(`#${id}>.toast`);
    const classes = container.classList;
    classes.add(cls);
    const body = container.querySelector('.toast-body');
    body.textContent = message;
    container.addEventListener('hidden.bs.toast', () => {
        classes.remove(cls);
        body.textContent = '';
    }, { once: true });
    const toast = bootstrap.Toast.getOrCreateInstance(container);
    toast.show();
}

export default {
    install: (app) => {
        app.config.globalProperties.$toast = {
            success: (id, msg) => {
                showToast(id, 'text-bg-success', msg);
            },
            error: (id, msg) => {
                showToast(id, 'text-bg-danger', msg);
            }
        }
    }
};
