import { shallowMount } from '@vue/test-utils'
import Footer from '@/components/Footer.vue'

describe('Footer.vue', () => {
    test('footer snapshot', () => {
      const wrapper = shallowMount(Footer)
      expect(wrapper).toMatchSnapshot()
    })
  })