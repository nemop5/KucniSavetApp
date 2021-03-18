 <template>
	<main main class="main">
		<div  v-show="show" class="wrap">
			<!-- item -->
			<div class="item-row" v-for="task in tasks" v-bind:key="task.id">
				<div class="check-flag">
					<span class="small-text-label">Title</span>
					<span class="small-text-label hours">Hours</span>
					<span class="check-flag-label">{{ task.title }}</span>
					<span class="hours-box">{{ task.hours }}</span>
				</div>
			</div>
			<div v-show="this.$store.state.totalHours !== 0" class="total align-right">
				<label for="" class="total-label">Total:</label>
				<input class="total-input" type="text" v-model="this.$store.state.totalHours" readonly>
			</div>
		</div>
		<div v-show="this.$store.state.totalHours === 0" class="wrap">
			<div class="item-row">
				<div class="check-flag">
					<span class="check-flag-label">There is no task for requested date. You can relax.</span>
				</div>
			</div>
		</div>
	</main>
</template>

<script>
	import axios from 'axios'

	export default {
		data () {
      		return {
				show: true
      		}
		},
		computed: {
            tasks() {
                return this.$store.getters.tasks;
			}
		},
    	created() {
			this.$store.dispatch('getTasks')
    	},
		updated() {
			let totalHours = 0;
			let tasks = this.$store.state.tasks
    		tasks.forEach((element) => { totalHours += element.hours; })
			this.$store.dispatch('setTotalHours', totalHours)
		},
		errorCaptured() {
			if ( this.$store.state.totalHours === 0) {
				console.log("no tasks")
				this.show = false
			}
		}
  	}
</script>


