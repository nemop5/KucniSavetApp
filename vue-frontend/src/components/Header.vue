<template>
    <!-- header -->
	<header class="header">
        <!-- modal -->
        <b-modal ref="modal" hide-footer  title="Create task:">
            <b-form 
                id="create-task"
            >
                <b-form-group class="field-wrap">
                    <label class="label" for="title">Title:</label>
                    <b-form-input 
                        class="field" 
                        name="title"
                        type="text" 
                        v-model="title"
                    />
                </b-form-group>
                <b-form-group class="field-wrap">
                    <label class= "label" for="hours">Hours:</label>
                    <b-form-input 
                        class="field"
                        name="hours"
                        type="number"
                        min="0.5"
                        v-model="hours"
                    />
                </b-form-group>
            </b-form>
        <div class="btn-wrap align right" align="right">
            <b-button 
                class="btn"
                :disabled="title == '' || hours == ''"
                @click="createTask"  
            >Create</b-button>
        </div>
      </b-modal>

		<div class="wrap">
			<span class="btn-icon">
				<img 
                class="icon icon-plus js-modal-init" 
                src="../assets/icons/icon-plus.svg" 
                alt="Add New Item"
                @click="showModal"
                >
			</span>
			<div class="header-blockquote">
				<h1 class="header-quote">{{ quote.quote }}</h1>
				<div class="header-cite">- {{ quote.author }}</div>
			</div>
		</div>
		<div class="header-inner">
			<div class="wrap">
				<img class="logo" src="../assets/images/vegait-logo.svg" alt="VegaIT">
				<div class="date-wrap">
					<img class="icon" src="../assets/icons/icon-calendar.svg" alt="Calendar">
					<time>{{ $route.params.date }}</time>
				</div>
			</div>
		</div>
	</header>
</template>

<script>
    // import {mapActions} from 'vuex';
    import { quotes } from '../assets/data'
    import axios from 'axios'
    import { BModal, BFormGroup, BFormInput, BForm, BButton } from 'bootstrap-vue';

    export default {
        name: 'Header',
        components: {
            bModal: BModal,
            bForm: BForm,
            bFormGroup: BFormGroup,
            bFormInput: BFormInput,
            bButton: BButton
        },
        data() {
            return {
                quote: {},
                title: '',
                hours: '',
                isActive: true
            }
        },
        created() {
            let allQuotes = quotes
            let randomQuote =  allQuotes[Math.floor(Math.random() * allQuotes.length)]
            console.log(randomQuote)
            this.quote = randomQuote
        },
        methods: {
            showModal() {
                this.$refs['modal'].show()
            },
            hideModal() {
                this.$refs['modal'].hide()
            },
            reloadPage() {
                window.location.reload();
            },
            createTask() {
                /*************Value to configure maximum number of hours per day *************/
                 let maximHours = 24;
                /*****************************************************************************/
                let currentTotalHours = this.$store.getters.totalHours;
                let hoursToAdd = parseInt(this.hours); 

                if(currentTotalHours + hoursToAdd > maximHours) {
                    alert("You are trying to add task which exceeds the total number of hours for a current date(24h). Please reduce the amount of hours.");
                } else {
                    let taskDto = {
                    title: this.title,
                    hours: parseFloat(this.hours),
                    taskDate: this.$route.params.date
                    }

                    console.log(taskDto)
                    axios.post('http://localhost:8080/api/tasks', taskDto)
                    .catch((error) => {
                        console.log(error)
                    })
                    this.reloadPage()
                }
            }   
        }
    }
</script>