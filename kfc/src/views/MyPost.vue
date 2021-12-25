<template>
    <div id="mypost">
        <div class="nav">
            <van-nav-bar  left-text="返回" title="我的帖子" left-arrow @click-left="onClickLeft">
            <template #right>
                <van-icon name="ellipsis" size="18" />
            </template>
            </van-nav-bar>
        </div>


        <div class="tab">
            <van-tabs v-model="active">
            <van-tab title="主贴">
                <div v-if="post.length==0">
                    <van-empty
                    class="custom-image"
                    image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
                    description="这里是空的，去发表你的帖子吧"
                    />
                </div>
                <div v-else class="box" v-for="(post ,pid) in post" :key="pid">
                    <div class="top" @click="goDetail(post.pid)">
                        <span class="title">{{post.title}}</span><br>
                        <span class="content">{{post.content}}</span>
                    </div>
                    <img @click="goDetail(post.pid)" v-if="post.image" :src="post.image" alt="">
                    
                    <div class="low">
                        <van-grid :border="false" :column-num="3">
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
                            <van-grid-item>
                            <van-icon @click="DelPost(post.pid)" name="delete-o" color="#ee0a24" size="20"/>
                            </van-grid-item>
                            </van-grid>
                    </div>
                    
                </div>
            </van-tab>
            <van-tab title="评论">
                <div v-if="comment.length==0">
                    <van-empty
                    class="custom-image"
                    image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
                    description="这里是空的，去说点什么吧"
                    />
                </div>
                <div class="box" v-else v-for="(comment,cid) in comment" :key="cid">
                    <div class="user">
                        <img :src="icon" alt="">
                        <div class="right">
                            <span class="nickname">{{nickname}}</span><br>
                            <span class="time">{{comment.ctime}}</span>
                        </div>
                    </div>
                    <div class="reply" @click="goDetail(reply.pid)">
                        <span>评论：{{comment.comment}}<span v-if="comment.pictrue">[图片]</span></span><br><br>
                        <span>原贴：{{comment.posts[0].title}}</span><br><br><br>
                    </div>
                    <div class="del">
                        <van-icon @click="DelComment(reply.cid)" name="delete-o" color="#ee0a24" size="20"/>                      
                    </div>
                </div>
            </van-tab>
            <van-tab title="回复">
                <div v-if="reply.length==0">
                    <van-empty
                    class="custom-image"
                    image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
                    description="这里是空的，去说点什么吧"
                    />
                </div>
                <div class="box" v-else v-for="(reply,rid) in reply" :key="rid">
                    <div class="user">
                        <img :src="icon" alt="">
                        <div class="right">
                            <span class="nickname">{{nickname}}</span><br>
                            <span class="time">{{reply.rtime}}</span>
                        </div>
                    </div>
                    <div class="reply" @click="goDetail(reply.pid)">
                        <span>回复：{{reply.reply}}</span><br><br>
                        <span>{{reply.rusers[0].nickname}}：{{reply.comments[0].comment}}</span><br><br>
                        <span>原贴：{{reply.posts[0].title}}</span><br><br><br>
                    </div>
                    <div class="del">
                        <van-icon @click="DelReply(reply.rid)" name="delete-o" color="#ee0a24" size="20"/>                      
                    </div>
                </div>
            </van-tab>
            </van-tabs>
        </div>
    </div>
</template>

<script>
import { Dialog } from 'vant';
    export default {
        name:'MyPost',
        data(){
            return{
                active:2,
                icon:localStorage.getItem("icon"),
                nickname:localStorage.getItem("nickname"),
                post:[],
                comment:[],
                reply:[],
            }
        },
        created(){
            let uid=localStorage.getItem("uid")
            console.log(uid)
            this.getMyPost(uid);
            this.getMyComment(uid);
            this.getMyReply(uid);
        },
        methods:{
            // 返回
            onClickLeft(){
                 this.$router.go(-1)
            },
            getMyPost(uid){
                this.axios({
                    url:'/Person',
                    method:'get',
                    params:{uid}
                }).then(res=>{
                    console.log('帖子')
                    console.log(res)
                    this.post=res.data;
                }).catch(err=>{})
            },
            getMyComment(uid){
                this.axios({
                    url:'/MyComment',
                    method:'get',
                    params:{uid}
                }).then(res=>{
                    this.comment=res.data
                    console.log('评论')
                    console.log(res)
                }).catch(err=>{})
            },
            getMyReply(uid){
                this.axios({
                    url:'/getMyReply',
                    method:'get',
                    params:{uid}
                }).then(res=>{
                    this.reply=res.data
                    console.log('回复')
                    console.log(res)
                }).catch(err=>{})
            },
            // 详情
            goDetail(pid){
            this.$router.push({name:'Detail',params:{pid}})
            },
            DelComment(cid){
                Dialog.confirm({
                title: '提示',
                message: '是否删除此条评论，删除后不可恢复！！！',
                })
                .then(() => {
                    // this.$toast('删除')
                    this.axios({
                        url:'/DelComment',
                        method:'post',
                        params:{cid}
                    }).then(res=>{
                        if(res.data==true){
                            this.$toast('删除成功！');
                            let uid=localStorage.getItem("uid")
                            this.getMyComment(uid);
                        }else{
                            this.$toast('删除失败！')
                        }
                    })
                    // on confirm
                })
                .catch(() => {
                    // on cancel
                });
            },
            DelPost(pid){
                Dialog.confirm({
                title: '提示',
                message: '是否删除这篇帖子，删除后不可恢复！！！',
                })
                .then(() => {
                    // this.$toast('删除')
                    this.axios({
                        url:'/DelPost',
                        method:'post',
                        params:{pid}
                    }).then(res=>{
                        if(res.data==true){
                            this.$toast('删除成功！');
                            let uid=localStorage.getItem("uid")
                            this.getMyPost(uid);
                        }else{
                            this.$toast('删除失败！')
                        }
                    })
                    // on confirm
                })
                .catch(() => {
                    // on cancel
                });
            },
            DelReply(rid){
                Dialog.confirm({
                title: '提示',
                message: '是否删除这条回复，删除后不可恢复！！！',
                })
                .then(() => {
                    // this.$toast('删除')
                    this.axios({
                        url:'/DelReply',
                        method:'post',
                        params:{rid}
                    }).then(res=>{
                        if(res.data==true){
                            this.$toast('删除成功！');
                            let uid=localStorage.getItem("uid")
                            this.getMyReply(uid);
                        }else{
                            this.$toast('删除失败！')
                        }
                    })
                    // on confirm
                })
                .catch(() => {
                    // on cancel
                });
            },
        },
    }
</script>

<style lang="less" scoped>
#mypost{
    margin-top: -60px;
    margin-bottom: 60px;
    .nav{
    width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
    }
    .tab{
        margin-top: 50px;

        .box{
            width: 94%;
            // height: 300px;
            margin: 10px;
            margin-top: 20px;
            border-radius: 10px;
            background: rgb(253, 253, 253);
            // display: flex;
            .top{
                margin-top: 10px;
                margin-left: 10px;
                width: 90%;
                padding: 10px;
                // height: 50px;
                // background: yellow;
                text-align: left;
                .title{
                    font-size: 18px;
                    font-weight: 560;
                }
                .content{
                    
                    font-size: 14px;
                }

            }
            
            img{
                margin-top: 10px;
                margin-bottom: 10px;
                width: 320px;
                height: 320px;
                object-fit: cover;
                border-radius: 10px;
            }


            .user{
                
                margin: 10px;
                
                display: flex;
                // background: lightcoral;
                img{
                    margin: 5px;
                    width: 40px;
                    height: 40px;
                    object-fit: cover;
                    border-radius: 10px;
                }
                .right{
                    margin-left: 5px;
                    margin-top:5px ;
                    margin-bottom: 5px;
                    text-align: left;
                    .nickname{
                        font-size: 16px;
                        font-weight: 690px;
                    }
                    
                }

            }
            .reply{
                text-align: left;
                font-size: 16px;
                margin-left: 20px;
                span{
                    
                }
            }
            .del{
                // width: 100%;
                 margin-right: 10px;
                margin-top: -30px;
                float: right;
            }
        }
    }
}
</style>