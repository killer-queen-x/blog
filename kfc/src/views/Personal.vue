<template>
<!-- 查看个人页面 -->
    <div id="personal">
        <div class="tou">
        <div class="nav">
            <van-nav-bar  left-text="返回" left-arrow @click-left="onClickLeft" @click-right="goChat(user.uid)" >
            <template #right>
                <van-icon v-if="user.uid!=uid" name="envelop-o" size="18" />
            </template>
            </van-nav-bar>
        </div>
        <div class="person">

            <div><img :src="user.icon" alt=""></div>
            <div class="pername">
                {{user.nickname}}
                <br><span>{{user.sign}}</span>
                </div>          

        </div>
        </div>
        <div style="width:100%;height:30px;"></div>

        

            <div class="list" v-for="(post,pid) in list" :key="pid" >

                <div class="top">
                    <div class="user" @click="goPerson()">
                    <div class="icons" >
                        <img :src="user.icon" alt="">
                        <!-- <img src="https://img0.baidu.com/it/u=4174801805,584386614&fm=26&fmt=auto" alt=""> -->
                        </div>
                    <div class="nickname">{{user.nickname}}</div>
                    </div>
                    <div class="time" >{{post.time}}</div>
                </div>

                <div class="mid" @click="goDetail(post.pid)">
                    <div class="title">{{post.title}}</div>
                    <div class="content">{{post.content}}</div>
                    <div class="image">
                    <img v-if="post.image"
                        :src="post.image"
                    />
                    </div>
                </div>


                <!-- <div class="low">
                   <van-grid :border="false" :column-num="2">
                        <van-grid-item>
                        <van-badge :content="post.comments.length+post.replies.length" color="#1989fa">
                        <van-icon name="comment-o" size="20" />
                        </van-badge>
                        </van-grid-item>
                        <van-grid-item>
                        <van-badge :content="post.goods.length" color="#1989fa">
                        <van-icon name="good-job-o" size="20" />
                        </van-badge>
                        </van-grid-item>
                    </van-grid>
                </div> -->


            </div>
        
    </div>
</template>

<script>
    export default {
        name:'Personal',
        data(){
            return{
                uid:localStorage.getItem("uid"),
                user:[],
                list:[],
            }
        },
        created(){
            let uid=this.$route.params.uid;
            if(uid){
            localStorage.setItem('personId',uid);
            // console.log(uid);
            this.getUser(uid);
            this.getList(uid);
            }else{
                let uid=localStorage.getItem('personId');
                this.getUser(uid);
                this.getList(uid);
            }
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
            // 用户信息
            getUser(uid){
                this.axios({
                        method:"get",
                        url:'/GetUser',
                        params:{uid}
                    }).then(res=>{
                        this.user=res.data[0];
                        console.log(res.data);
                        
                    }).catch(res=>{
                        console.log(404)
                    })
            },
            // 获取帖子
            getList(uid){
      
            console.log("发起数据请求")

            this.axios({
                        method:"get",
                        url:'/Person',
                        params:{uid}
                    }).then(res=>{
                        this.list=res.data;
                        console.log(this.list);
                        
                    }).catch(res=>{
                        console.log(404)
                    })
            },
            goChat(otherID){
                if(!localStorage.getItem('uid')){
                    this.$toast('请先登录！');
                    this.$router.push({name:'Login'})
                }else{
                this.$router.push({name:'Chat',params:{otherID}});
                }
            },
        }
    }
</script>

<style lang="less" scoped>
#personal{
    width: 100%;
    height: 100%;
    background: rgb(240, 236, 236);
    margin-top: 80px;
    // margin-bottom: 60px;
    .tou{
        width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
    .person{
        width: 100%;
        height: 120px;
        background: white;
        img{
            float: left;
            margin-top: 20px;
            margin-left: 20px;
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 50%;
            
        }
        .pername{
            text-align: left;
            float: left;
            margin-top: 20px;
            margin-left: 20px;
            font-size: 20px;
            font-weight: 700;
            span{
              font-size: 8px;
              font-weight: 100;
          }
        }
    }
    }


    
    
        .list{
            width: 94%;
            margin-top: 30px;
            margin-left: 1%;
            border-radius: 10px;
            background: white;
            margin: 10px;

            .top{
            width: 100%;
            display: flex;
            justify-content: space-between;

            .user{
                display: flex;
                .icons{
                margin: 5px;
                margin-left: 10px;
                // background: black;
                // height: 30px;
                // width: 30px;
                // border-radius: 35%;
                img{
                        width: 30px;
                        height: 30px;
                        object-fit: cover;
                        border-radius: 35%;
                }
                }
                .nickname{
                margin: 5px;
                font-size: 14px;
                font-weight: 530;
                }
            }
            .time{
                margin: 5px;
                color: rgb(173, 169, 169);
            }
            }

            .mid{
            width: 100%;
            text-align: left;

            .title{
                margin: 10px;
                font-size: 16px;
                font-weight: 550;
            }

            .content{
                margin: 10px;
                font-size: 14px;
                font-weight: 500;
            }
            .image{
                img{
                width: 300px;
                height: 300px;
                margin: 25px;
                object-fit: cover;
                border-radius: 10%;
                }
            }
            
            }

            .low{
            width: 90%;
            margin: 10px;

            }
        }
    
}
</style>