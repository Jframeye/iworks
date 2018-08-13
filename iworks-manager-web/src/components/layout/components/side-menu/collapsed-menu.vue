<template>
  <Dropdown @on-click="handleClick" transer placement="right-start">
    <a class="drop-menu-a" type="text" :style="{textAlign: !hideTitle ? 'left' : ''}">
      <common-icon :size="rootIconSize" :color="color" :type="parentItem.icon"/>
      <span class="menu-title" v-if="!hideTitle">{{ title(parentItem) }}</span>
      <Icon v-if="!hideTitle" :size="10" :color="color" type="chevron-right" style="float: right;margin-top: 4px;"/>
    </a>
    <DropdownMenu slot="list">
      <template v-for="child in parentItem.children">
        <collapsed-menu v-if="showItself(child)" :icon-size="iconSize" :parent-item="child" :key="`drop-${child.name}`"></collapsed-menu>
        <DropdownItem v-else :key="`drop-${child.name}`" :name="child.name">
          <common-icon :size="iconSize" :type="child.icon"/>
          <span class="menu-title">{{ title(child) }}</span>
        </DropdownItem>
      </template>
    </DropdownMenu>
  </Dropdown>
</template>
<script>
import mixin from './mixin'

export default {
  name: 'CollapsedMenu',
  mixins: [ mixin ],
  props: {
    parentItem: {
      type: Object,
      default: () => {}
    },
    theme: String,
    iconSize: Number,
    hideTitle: {
      type: Boolean,
      default: false
    },
    rootIconSize: {
      type: Number,
      default: 16
    }
  },
  methods: {
    handleClick (name) {
      this.$emit('on-click', name)
    }
  }
}
</script>
