import CommonIcon from '../../../common-icon'
export default {
  components: {
    CommonIcon
  },
  methods: {
    onlyOneChildren (item) {
      return item.children && item.children.length === 1
    },
    /**
     * 是否显示自己
     * 至少两个子菜单，或者 showAlways=true 时，显示
     * 否则不显示
     * @param {*} item 
     */
    showItself (item) {
      return item.children && (item.children.length > 1 || (item.meta && item.meta.showAlways))
    },
    /**
     * 菜单名称
     * @param {*} item 
     */
    title (item) {
      return (item.meta && item.meta.title) || item.name
    },
    /**
     * 菜单的 name 属性
     * @param {*} item 
     * @param {*} children0 
     */
    menuname (item, children0) {
      return item.href ? `isTurnByHref_${item.href}` : (children0 ? item.children[0].name : item.name)
    },
    color () {
      return this.theme === 'dark' ? '#fff' : '#495060'
    }
  }
}
