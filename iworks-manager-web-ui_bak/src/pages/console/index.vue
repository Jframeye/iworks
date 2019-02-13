<template>
  <div class="container" v-cloak>
    <el-row class="row" style="height: 60px;line-height: 60px">
        <el-col class="col" style="width: 100px;line-height: 60px;text-align: center;background-color:#17B9E6 ">
            <a href="/">
                <img src="http://doclever.cn:8090/logo.png" style="width:40px;height:40px;margin-top: 10px">
            </a>
        </el-col>
        <el-col class="col" style="width: calc(100vw - 100px);background-color: white;box-shadow: rgba(0, 0, 0, 0.15) 0px 1px 3px 0px; ">
            <el-row class="row" style="text-align: center">
                <el-col class="col" :span="6">
                    <!-- <el-tooltip class="item" effect="dark" :content="backTitle" placement="bottom" v-if="(type==0 && !session.projectId && session.teamId) || (type==1 && session.teamId)">
                        <el-button icon="el-icon-back" type="text" size="medium" style="font-size: 20px;float: left;margin-left: 20px;margin-top: 8px" @click="back"></el-button>
                    </el-tooltip>
                    <el-tooltip class="item" effect="dark" :content="backTitle" placement="bottom" v-else-if="type==0 && session.projectId">
                        <el-button icon="el-icon-back" type="text" size="medium" style="font-size: 20px;float: left;margin-left: 20px;margin-top: 8px" @click="back"></el-button>
                    </el-tooltip>
                    <span v-else>
                        &nbsp;
                    </span> -->
                    <span>
                        &nbsp;
                    </span>
                </el-col>
                <el-col class="col" :span="12" style="font-size: 15px;font-weight: bold;color:#17B9E6 ">
                    測試
                </el-col>
                <el-col class="col" :span="6" style="height: 60px;text-align: right;padding-right: 20px">
                    <img src="@/assets/logo_22.png" style="width: 40px;height: 40px; border-radius:50%;margin-top: 10px">&nbsp;
                    <el-dropdown style="top: -15px;">
                        <span class="el-dropdown-link" style="color: #50bfff;cursor: pointer">
                            <span>
                                叶宏梁
                            </span>
                            <i class="el-icon-caret-bottom el-icon--right"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="update">检查更新</el-dropdown-item>
                            <el-dropdown-item command="help">帮助中心</el-dropdown-item>
                            <el-dropdown-item command="quit">退出</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
    <el-row class="row" style="height: calc(100vh - 60px);position: fixed">
        <el-col class="col" style="width: 100px;background-color: #333333">
            <el-row class="row" style="height: 80px;text-align: center;font-size: 14px;cursor: pointer" :style="{color:type==0?'white':'#888888',backgroundColor:type==0?'#333333':''}" @click.native="type=0">
                <mysvg src="@/assets/svg/xiangmu.svg" style="width: 32px;height: 32px;margin-top: 15px;" :color="type==0?'white':'#888888'" @click="type=0"></mysvg><br>
                项目
            </el-row>
            <el-row class="row" style="height: 80px;text-align: center;font-size: 14px;cursor: pointer" :style="{color:type==1?'white':'#888888',backgroundColor:type==1?'#333333':''}" @click.native="type=1">
                <mysvg src="@/assets/svg/tuandui.svg" style="width: 32px;height: 32px;margin-top: 15px;" :color="type==1?'white':'#888888'" @click="type=1"></mysvg><br>
                团队
            </el-row>
            <el-row class="row" style="height: 80px;text-align: center;font-size: 14px;cursor: pointer" :style="{color:type==2?'white':'#888888',backgroundColor:type==2?'#333333':''}" @click.native="type=2">
                <mysvg src="@/assets/svg/shezhi.svg" style="width: 32px;height: 32px;margin-top: 15px;" :color="type==2?'white':'#888888'" @click="type=2"></mysvg><br>
                设置
            </el-row>
            <el-row class="row" style="height: 80px;text-align: center;font-size: 14px;cursor: pointer" :style="{color:type==3?'white':'#888888',backgroundColor:type==3?'#333333':''}" @click.native="type=3">
                <mysvg src="@/assets/svg/xiaoxi.svg" style="width: 32px;height: 32px;margin-top: 15px;" :color="type==3?'white':'#888888'" @click="type=3"></mysvg><br>
                消息
                <div style="position: absolute;width: 10px;height: 10px;border-radius: 5px;background-color: red;top: 10px;right: 25px">
                </div>
            </el-row>
        </el-col>
    </el-row>
</div>
</template>

<script>
import svg from "@/components/svg.vue";

export default {
  name: 'console',
  components:{
    "mysvg": svg
  },
  data () {
    return {
      type: 0
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
