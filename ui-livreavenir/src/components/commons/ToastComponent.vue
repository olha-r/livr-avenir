<script>
export default {
    name: "ToastComponent",
    props: {
        id: {
            type: String,
            default: null
        }
    }
}
</script>
<template>
    <div :id="id" class="toast-container p-3 top-0 start-50 translate-middle-x">
        <div class="toast shadow-sm">
            <div class="toast-header">
                <strong class="text-dark me-auto">Message</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
            </div>
            <div class="toast-body"></div>
        </div>
    </div>
</template>
