<template>
  <Submenu :name="parentItem.name">
    <template slot="title">
      <Icon :type="parentItem.icon"/>
      <span>{{ title(parentItem) }}</span>
    </template>
    <template v-for="item in parentItem.children">
      <template v-if="onlyOneChildren(item)">
        <side-menu-item v-if="showItself(item)" :key="`menu-${item.name}`" :parent-item="item"></side-menu-item>
        <menu-item v-else :name="menuname(item, true)" :key="`menu-${item.children[0].name}`">
          <common-icon :type="item.children[0].icon || ''"/>
          <span>{{ title(item.children[0]) }}</span>
        </menu-item>
      </template>
      <template v-else>
        <side-menu-item v-if="showItself(item)" :key="`menu-${item.name}`" :parent-item="item"></side-menu-item>
        <menu-item v-else :name="menuname(item)" :key="`menu-${item.name}`">
          <common-icon :type="item.icon || ''"/>
          <span>{{ title(item) }}</span>
        </menu-item>
      </template>
    </template>
  </Submenu>
</template>
<script>
import mixin from './mixin'
export default {
  name: 'SideMenuItem',
  mixins: [ mixin ],
  props: {
    parentItem: {
      type: Object,
      default: () => {}
    },
    theme: String,
    iconSize: Number
  }
}
</script>
