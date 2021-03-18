<template>
	<div class="page-wrap">
		<!-- header -->
		<app-header></app-header>
		<!-- main -->
		<app-main></app-main>
		<!-- footer -->
		<app-footer></app-footer>
	</div>
</template>

<script>
    import Header from '../components/Header.vue';
	import Main from '../components/Main.vue';
	import Footer from '../components/Footer.vue';

    export default {
		name: 'AppTemplate',
        components: {
            appHeader: Header,
			appMain: Main,
			appFooter: Footer
        },
    }
</script>