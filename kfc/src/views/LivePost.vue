<template>
    <div id="live">
        <div class="nav">
            <van-nav-bar  left-text="返回" title="我的收藏" left-arrow @click-left="onClickLeft">
            <template #right>
                <van-icon name="ellipsis" size="18" />
            </template>
            </van-nav-bar>
        </div>


        <div v-if="live.length==0">
                    <van-empty
                    class="custom-image"
                    image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
                    description="暂无收藏"
                    />
                </div>
        <div class="list" v-else v-for="(live ,lid) in live" :key="lid" @click="goDetail(live.pid)">
            <img :src="live.posts[0].image" alt="">
            <span>{{live.posts[0].title}}</span>
        </div>
    </div>
</template>

<script>
import Vue from 'vue';
    export default {
        name:'LivePost',
        data(){
            return{
                live:[],
            }
        },
        created(){
            let uid=localStorage.getItem('uid');
            this.getLive(uid);
        },
        methods:{
            // 返回
            onClickLeft(){
                 this.$router.go(-1)
            },
            // 详情
            goDetail(pid){
            this.$router.push({name:'Detail',params:{pid}})
            },
            // 获取
            getLive(uid){
                this.axios({
                    url:'/Live',
                    method:'get',
                    params:{uid}
                }).then(res=>{
                    console.log("收藏：");
                    console.log(res);
                    this.live=res.data;
                }).catch(err=>{})
            },
        },
    }
</script>

<style lang="less" scoped>
#live{
// margin-top: 0px;
    margin-bottom: 60px;
    .nav{
    width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
    }

    .list{
        margin-top: 10px;
        margin-left: 5%;
        width: 90%;
        // height: 100px;
        background: rgb(241, 242, 245);
        border-radius: 10px;
        display: flex;
        img{
            width: 100px;
            height: 100px;
            margin: 10px;
            border-radius: 10px;
            object-fit: cover;
        }
        span{
            margin-top: 15px;
            margin-left: 10px;
            font-size: 20px;
            font-weight: 700;
        }
    }
}
</style>