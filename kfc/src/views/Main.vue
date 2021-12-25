<template>
    <div id="main">
        <router-view></router-view>
        <van-tabbar v-model="active" route>
            <van-tabbar-item to="/home" icon="home-o">首页</van-tabbar-item>
            <van-tabbar-item to="/message" icon="envelop-o">消息</van-tabbar-item>
            <van-tabbar-item to="/my" icon="contact">我的</van-tabbar-item>
        </van-tabbar>

    </div>
</template>

<script>
    export default {
        name:'Main',
        data(){
            return{
                active:''
            }
        }
        
    }
</script>

<style lang="less" scoped>
#main{
  width: 100%;
  height: 100%;
  background: rgb(240, 236, 236);
}
</style>