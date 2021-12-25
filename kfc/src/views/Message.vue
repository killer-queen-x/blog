<template>
<!-- 消息页面 -->
    <div id="message" >
        <div class="nav">
            <van-nav-bar   title="消息" @click-right="goNotice()">
                <template #right>
                    <van-badge dot>
                        <van-icon name="volume-o" size="18" />
                    </van-badge>
                </template>
            </van-nav-bar>
            
        </div>
        <div v-if="message.length==0">
            <van-empty
                class="custom-image"
                image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
                description="暂无消息"
            />
        </div>
        <div v-else class="list" @click="goChat(message.users[0].uid)" v-for="(message,mid) in message" :key="mid">
            <img :src="message.users[0].icon" alt=""/>
            
            <div class="box">
            <div class="name">{{message.users[0].nickname}}<span>{{message.sendtime}}</span></div>
            <div class="content">{{message.message}}</div>
            </div>
        </div>
        
    </div>
</template>

<script>
    export default {
        name:"Message",
        data(){
            return{
                message:[]
            }
        },
        created(){
            let uid=localStorage.getItem("uid")
            console.log(uid)
            this.getMessage(uid);
        },
        methods:{
            goNotice(){
                this.$router.push({name:'Notice'})
            },
            goChat(otherID){
                this.$router.push({name:'Chat',params:{otherID}})
            },
            getMessage(uid){
                this.axios({
                    url:'Message',
                    method:'get',
                    params:{uid}

                }).then(res=>{
                    this.message=res.data;
                    console.log(res);
                }).catch(err=>{})
            },
        }
    }
</script>

<style lang="less" scoped>
#message{
    width: 100%;
    height: 100%;
    background: rgb(240, 236, 236);
    .nav{
    width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
    }
    .list{
        // width: 100%;
        height: 50px;
        margin: 1px;
        background: white;
        display: flex;

    
        img{
            margin: 5px;
            width: 40px;
            height: 40px;
            object-fit: cover;
            border-radius: 35%;
          }

        .box{
            text-align: left;
            .name{
                width: 100%;
                margin: 8px;
                font-size: 15px;
                span{
                    font-size: 5px;
                    float: right;
                    // margin-right: -50px;
                    color: rgb(170, 165, 163);
                }
            }
            .content{
                margin-left: 8px;
                color: rgb(170, 165, 163);
            }
        }
    }
}
</style>