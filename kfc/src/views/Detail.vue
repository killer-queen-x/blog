<template>
<!-- 详情页面 -->
    <div id="detail">
        <div class="nav">
            
            <van-nav-bar  left-text="返回" left-arrow @click-left="onClickLeft">
            <template #right>
                <van-icon name="ellipsis" size="18" />
            </template>
            </van-nav-bar>
        </div>

        <div style="width:100%;height:50px;"></div>

    <div>
        <div class="title">
            {{post.title}}
        </div>
        <div class="main" >
            
                <img :src="post.users[0].icon" @click="goPerson(post.uid)" alt="">
            
            <div class="box">
                <div class="nickname">
                    {{post.users[0].nickname}}
                    <span>
                    <van-badge :content="goods.length" color="#1989fa">
                        <van-icon v-if="good" @click="DelGood(post.pid,uid)" color="#ee0a24" name="like" size="20" />
                        <van-icon v-else @click="AddGood()" name="like-o" size="20" />
                    </van-badge>
                    </span>
                </div>
                <div class="data">
                    <label >{{post.time}}</label>
                    <label >{{post.address}}</label>
                </div>
            </div>
        </div>
        
        <div class="content">{{post.content}}</div>

        <div>
            <img :src="post.image" alt="">
        </div>
    </div>
    <van-divider />


    <div class="comments" v-for="(comment,cid) in comment" :key="cid">
        <div class="left"><img :src="comment.cusers[0].icon" alt=""></div>
        <div class="right">
            <div class="name">
                {{comment.cusers[0].nickname}}
                <!-- <label v-if="comment.uid==post.uid">博主</label> -->
                <van-tag v-if="comment.uid==post.uid" round plain type="primary">博主</van-tag>
                </div>
            <div class="date">{{comment.ctime}}</div>
            <van-divider />
            <div class="content" @click="showPopup2(comment.cid,comment.uid,comment.cusers[0].nickname,comment.comment)">{{comment.comment}}</div>
            <img v-if="comment.pictrue" :src="comment.pictrue" @click="showPopup2(comment.cid,comment.uid,comment.cusers[0].nickname,comment.comment)" alt="">
            <van-divider />
            <div class="reply">
                <div  v-for="(reply,rid) in reply" :key="rid" >
                    <div class="re" v-if="comment.cid==reply.cid">
                        <div class="replyname">{{reply.rusers[0].nickname}}<van-tag v-if="reply.rusers[0].uid==post.uid" round plain type="primary">博主</van-tag>:</div>
                        <div class="replycontent">{{reply.reply}}</div>
                        
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    

    
        <div>
                <van-popup
                    v-model="showC"
                    closeable
                    close-icon="close"
                    position="bottom"
                    :style="{ height: '30%' }"
                    >

                    <div class="entercom">
                    
                        <div class="upload">
                            <input v-show="false" type="file" accept="image/*" @change="tirggerFile($event)" ref="input" />
                            <div style="width:30px;height:30px;border:1px none;text-align:center;" @click="openImg">
                            
                            <van-icon  name="add-o" size="30"/>
                            <!-- <img style="height:100%;width:100%;" v-if="post.image!=''" :src="comimg" /> -->
                            </div>
                        </div>
                        <input v-model="comm" type="text"  placeholder="请输入评论..." />
                        <div class="send"><van-button @click="sendC()" size="small" round type="info">发送</van-button></div>
                    
                    </div>
                        <img v-if="comimg!=''" :src="comimg" alt="">
                </van-popup>
        </div>


        <div>
            <van-popup
                    v-model="showR"
                    closeable
                    close-icon="close"
                    position="bottom"
                    :style="{ height: '15%' }"
                    >
                    <div class="last">回复 {{cname}}:{{content}}</div>
                    <div class="enterre">
                        <input v-model="rep" type="text" placeholder="请输入回复..." />
                        <div class="send"><van-button @click="sendR()" size="small" round type="info">发送</van-button></div>
                    
                    </div>
            </van-popup>
        </div>

    <div class="bottom">
        <van-goods-action>
        <input @click="showPopup" v-model="snull"  type="text" placeholder="  我来评论">

        <div class="c-num">
            <van-badge :content="comment.length+reply.length" color="#1989fa">
            <div class="child" ><van-icon name="comment-o" size="20"/></div>
            </van-badge>

            
            
        </div>
        
        </van-goods-action>
    </div>


    </div>
</template>

<script>
const unit=require('../assets/js/util');
    export default {
        name:'Detail',
        data(){
            return{
                uid:localStorage.getItem('uid'),
                snull:'',
                showC:false,
                comm:'',
                comimg:'',
                showR:false,
                cid:'',
                cuid:'',
                cname:'',
                content:'',
                rep:'',
                post:[],
                comment:[],
                reply:[],
                good:false,
                goods:[],
            }
        },
        created(){
            console.log(this.$route.params.pid);
            let pid=this.$route.params.pid;
            console.log(pid)
            if(pid){
            localStorage.setItem('pid',pid);
            this.getDetail(pid);
            this.getComment(pid);
            this.getReply(pid);
            this.getGoods(pid);
            }else{
                let pid=localStorage.getItem("pid");
                this.getDetail(pid);
                this.getComment(pid);
                this.getReply(pid);
                this.getGoods(pid);
            }
        },
        methods:{
            // 返回
            onClickLeft(){
                 this.$router.go(-1)
            },
            
            // 个人
            goPerson(uid){
            this.$router.push({name:'Personal',params:{uid}})
            },
            // 获取详情
            getDetail(pid){
                this.axios({
                    method:"get",
                    url:'/getDetail',
                    params:{pid}
                }).then(res=>{
                    this.post=res.data[0];
                    console.log("详情：");
                    console.log(this.post);
                }).catch(err=>{})
            },
            // 获取点赞
            getGoods(pid){
                this.axios({
                    url:'GetGoods',
                    method:'get',
                    params:{pid}
                }).then(res=>{
                    console.log("点赞：");
                    console.log(res);
                    this.goods=res.data;
                    for(let i=0;i<res.data.length;i++){
                        // var that=this;
                        if(res.data[i].uid==localStorage.getItem("uid")){                          
                             this.good=true;                            
                        }
                    }
                    // console.log(this.good)
                }).catch(err=>{})
            },
            AddGood(){
                this.axios({
                    url:'/AddGood',
                    method:'post',
                    data:{
                        pid:this.post.pid,
                        uid:localStorage.getItem('uid')
                    }
                }).then(res=>{
                    this.getGoods(this.post.pid);
                }).catch(err=>{})
            },
            // 取消点赞
            DelGood(pid,uid){
                this.axios({
                    url:'/DelGood',
                    method:'post',
                    params:{pid,uid}
                }).then(res=>{
                    this.getGoods(this.post.pid);
                }).catch(err=>{});
                this.good=''
            },
            // 获取评论
            getComment(pid){
                this.axios({
                    method:'get',
                    url:'/getComment',
                    params:{pid}
                }).then(res=>{
                    this.comment=res.data;
                    console.log("评论：");
                    console.log(res);
                }).catch(err=>{})
            },
            // 获取回复
            getReply(pid){
                this.axios({
                    method:'get',
                    url:'/getReply',
                    params:{pid}
                }).then(res=>{
                    this.reply=res.data;
                    console.log("回复：");
                    console.log(this.reply);
                })
            },
            showPopup(){
                this.showC=true
            },
            tirggerFile: function(event) {
            let file = event.target.files[0];
            let url = "";
            var reader = new FileReader();
            reader.readAsDataURL(file);
            let that = this;
            reader.onload = function(e) {
                url = this.result.substring(this.result.indexOf(",") + 1);
                that.comimg = "data:image/png;base64," + url;
                // console.log(that.post)
                // that.$refs['imgimg'].setAttribute('src','data:image/png;base64,'+url);
            };
            },
            openImg() {
            this.$refs.input.click();
            },
            sendC(){
                if(!localStorage.getItem('uid')){
                    this.$toast('请先登录！');
                    this.$router.push({name:'Login'})
                }else{


                if(this.comm==''&&this.comimg==''){
                    this.$toast('评论内容不能为空！');
                }else{
                
                this.axios({
                    url:'/AddComment',
                    method:'post',
                    data:{
                        pid:this.post.pid,
                        puid:this.post.uid,
                        uid:localStorage.getItem("uid"),
                        comment:this.comm,
                        ctime:unit.formatTime(new Date()),
                        pictrue:this.comimg
                    }
                }).then(res=>{
                    console.log(res);
                    let pid=this.post.pid;
                    this.getComment(pid);
                    this.getReply(pid);
                }).catch(err=>{})
                this.comm='',
                this.comimg='',
                this.showC=false
                } 

                }
            },
            showPopup2(cid,cuid,cname,content){
                this.showR=true,
                this.cid=cid,
                this.cuid=cuid,
                this.cname=cname,
                this.content=content
            },
            sendR(){

                if(!localStorage.getItem('uid')){
                    this.$toast('请先登录！');
                    this.$router.push({name:'Login'})
                }else{

                if(this.rep==''){
                    this.$toast('回复内容不能为空！');
                }else{
                this.axios({
                    url:'/AddReply',
                    method:'post',
                    data:{
                        pid:this.post.pid,
                        cid:this.cid,
                        cuid:this.cuid,
                        uid:localStorage.getItem("uid"),
                        reply:this.rep,
                        rtime:unit.formatTime(new Date()),
                    }
                }).then(res=>{
                    console.log(res);
                    let pid=this.post.pid;
                    this.getComment(pid);
                    this.getReply(pid);
                }).catch(err=>{})
                this.rep='',
                this.showR=false
                }

                }
            },
        }
    }
</script>

<style lang="less" scoped>
#detail{
    margin-top: -60px;
    margin-bottom: 60px;
    .nav{
    width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
    }

    .title{
    
        margin: 5px 10px;
        text-align: left;
        font-size: 22px;
        font-weight: 700;
        
    }
    .main{
        width: 100%;
        display: flex;
        
        
            img{
            margin: 5px 10px;
            width: 40px;
            height: 40px;
            object-fit: cover;
            border-radius: 50%;
            }
        
        .box{
            width: 80%;
            margin: 5px;
            text-align: left;
            .nickname{
                font-size: 14px;
                font-weight: 600px;
                span{
            float: right;
            margin-top: 5px;
            margin-right: 10px;
        }
            }

            .data{
                width: 100%;
                display: flex;
                margin: 5px 0;
                justify-content: space-between;
                label{
                    color: rgb(173, 169, 169);
                }
            }
        }
    }

    .content{
        margin: 5px;
        text-align: left;
        font-size: 16px;
    }
    img{
        width: 90%;
        margin: 10px;
        object-fit: cover;
        border-radius: 10px;
    }
    .comments{
        width: 100%;
        margin-top: 30px;
        display: flex;

        .left{
            width: 15%;
            img{
            margin: 5px 10px;
            width: 35px;
            height: 35px;
            object-fit: cover;
            border-radius: 50%;
            }
        }
        .right{
            width: 100%;
            text-align: left;
            .name{
                margin:5px 5px;
                font-size: 16px;
                font-weight: 600px;
                label{
                    margin-left:5px ;
                    color: #1989fa;
                    border:1px solid #1989fa;
                    border-radius:5px ;
                    font-size: 3px;
                }
            }
            .date{
                margin:5px 5px;
                font-size: 8px;
                color: rgb(173, 169, 169);
            }
            .content{
                margin: 5px 0;
                font-size: 14px;
                font-weight: 600;
            }
            .reply{
                width: 95%;
                margin: 10px 0;
                border-radius: 5px;
                background: rgb(238, 237, 237);

                .re{
                    display: flex;
                .replyname{
                    margin: 5px;
                    font-size: 14px;
                    font-weight: 700;
                    color: rgb(107, 106, 106);
                }
                .replycontent{
                    margin: 5px;
                    font-size: 14px;
                }
                }
            }
        }

    }

    .entercom{
        margin-top: 50px;
        display: flex;

        .upload{
            margin: 10px;
            
        }
        input{
            margin: 10px;
            margin-left: 10px;
            width: 240px;
            height: 30px;
            background: rgb(248, 243, 243);
            border-radius: 20px;
            border: none;
        }
        .send{
            margin: 10px;
        }
    }

    .last{
        font-size: 14px;
        text-align: left;
        margin: 10px;
        color: rgb(151, 144, 144);
    }
    .enterre{
        margin-top: 20px;
        display: flex;

        
        input{
            margin: 10px;
            margin-left: 10px;
            width: 280px;
            height: 30px;
            background: rgb(248, 243, 243);
            border-radius: 20px;
            border: none;
        }
        .send{
            margin: 10px;
        }
    }

    .bottom{
        display: flex;
        input{
            margin: 10px;
            width: 180px;
            height: 30px;
            background: rgb(248, 243, 243);
            border-radius: 20px;
            border: none;
        }
        .c-num{
            margin-left: 80px;
        }
    }
}
</style>