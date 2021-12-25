<template>
    <div id="chat">
        <div class="nav">           
            <van-nav-bar  left-text="返回"  left-arrow @click-left="onClickLeft">
            <template #title>
                {{other.nickname}}
            </template>
            <template #right>
                <van-icon name="ellipsis" size="18" />
            </template>
            </van-nav-bar>
        </div>

        <div class="main">
            <div v-for="(chat ,index) in chat" :key="index">
                <!-- <div class="left" >
                    <img src="https://img0.baidu.com/it/u=4174801805,584386614&fm=26&fmt=auto" alt="">
                    <div class="p">
                        
                        1231313132131
                        </div>
                </div> -->

                <div class="right" v-if="chat.sendid==uid">
                    
                    <div class="p" v-if="chat.message">
                        {{chat.message}}
                    </div>
                    <img  :src="chat.photo" v-else style="width:100px;margin-right: 20px;border-radius:10px;" alt="">
                        <img class="img" :src="icon" @click="goMy()" alt="">
                </div>

                <div class="left" v-else>
                    <img class="img"  :src="other.icon" @click="goPerson(other.uid)" alt="">
                    
                    <div class="p" v-if="chat.message">
                        {{chat.message}}
                    </div>
                    <img  :src="chat.photo" v-else style="width:100px;margin-left: 20px;border-radius:10px;" alt="">
                </div>
            </div>
        </div>

        <div>
            <van-popup
                    v-model="show"
                    closeable
                    close-icon="close"
                    close-icon-position="top-left"
                    position="bottom"
                    :style="{ height: '30%' }"
                    >
                    <div class="choose" v-if="photo==''">
                        <div class="upload">
                            <input v-show="false" type="file" accept="image/*" @change="tirggerFile($event)" ref="input" />
                            <div style="width:30px;height:30px;border:1px none;text-align:center;" @click="openImg">
                            
                            <van-icon  name="add-o" size="30"/>
                            <!-- <img style="height:100%;width:100%;" v-if="post.image!=''" :src="comimg" /> -->
                            </div>
                        </div>
                    </div>
                    <div class="choose" v-else><van-button @click="send()" size="small" round type="info">发送</van-button></div>
                    <img :src="photo" style="width:90%;border-radius:10px;margin-top:20px;" alt="">
            </van-popup>
        </div>
        
        <div class="bottom">
            <van-tabbar>
                <div class="van-icon"><van-icon @click="showPopup()" name="add-o" size="30"/></div>
                <input v-model="message" type="text" placeholder="请输入..." />
                <div class="send"><van-button @click="send()" size="small" round type="info">发送</van-button></div>
            </van-tabbar>
        </div>
    
    </div>
</template>

<script>

const unit=require('../assets/js/util');
    export default {
        name:'Chat',
        data(){
            return{
                uid:localStorage.getItem("uid"),
                receiveid:localStorage.getItem("otherId"),
                other:[],
                message:'',
                chat:[],
                show:false,
                photo:'',
                icon:localStorage.getItem("icon"),
            }
        },
        created(){
            let otherId=this.$route.params.otherID;
            if(otherId){
            localStorage.setItem("otherId",JSON.stringify(otherId));
            let uid=localStorage.getItem("uid");
            this.getUser(otherId);
            this.getChat(uid,otherId);
            }else{
            let otherID=localStorage.getItem("otherId");
            let uid=localStorage.getItem("uid");
            this.getUser(otherID);
            this.getChat(uid,otherID);
            }
        },
        methods:{
            // 返回
            onClickLeft(){
                // let otherId='';
                // localStorage.setItem("otherId",JSON.stringify(otherId));
                 this.$router.go(-1);
            },
            // 用户信息
            getUser(uid){
                this.axios({
                        method:"get",
                        url:'/GetUser',
                        params:{uid}
                    }).then(res=>{
                        this.other=res.data[0];
                        console.log(res);
                        
                    }).catch(res=>{
                        console.log(404)
                    })
            },
            send(){
                if(this.message==''&&this.photo==''){
                    this.$toast('信息不能为空！');
                }else{
                this.axios({
                    url:'/Send',
                    method:'post',
                    data:{
                        sendid:this.uid,
                        receiveid:this.receiveid,
                        message:this.message,
                        sendtime:unit.formatTime(new Date()),
                        photo:this.photo
                    }
                }).then(res=>{
                    console.log(res);
                    this.getChat(this.uid,this.receiveid);
                }).catch(err=>{})
                this.message='',
                this.show=false,
                this.photo=''
                }
            },
            getChat(uid,otherID){
                this.axios({
                    method:'get',
                    url:'/Chat',
                    params:{uid,otherID}
                }).then(res=>{
                    this.chat=res.data;
                    console.log(this.chat);
                    
                }).catch(err=>{})
                
            },
            showPopup(){
                this.show=true
            },
            tirggerFile: function(event) {
            let file = event.target.files[0];
            let url = "";
            var reader = new FileReader();
            reader.readAsDataURL(file);
            let that = this;
            reader.onload = function(e) {
                url = this.result.substring(this.result.indexOf(",") + 1);
                that.photo = "data:image/png;base64," + url;
                // console.log(that.post)
                // that.$refs['imgimg'].setAttribute('src','data:image/png;base64,'+url);
            };
            },
            openImg() {
            this.$refs.input.click();
            },
            // 个人
            goPerson(uid){
            this.$router.push({name:'Personal',params:{uid}})
            },
            goMy(){
                this.$router.push({name:'My'})
            },
        },
    }
</script>

<style lang="less" scoped>
#chat{
    margin-top: -60px;
    margin-bottom: 60px;
    .nav{
    width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
  }

    .main{
        margin-top: 40px;
        padding: 10px;
        height: 560px;
        background: rgb(171, 240, 245);
        overflow-y: auto;
        // display: flex;

        .left{
            width: 100%;
            margin-top: 10px;
            margin-left: 0px;
            display: flex;
            // background: yellow;

            .img{
                
                width: 40px;
                height: 40px;
                object-fit: cover;
                border-radius: 10%;
            }
            .p{
                
                margin-left: 20px;
                font-size: 14px;
                padding: 10px;
                position: relative;
                background: rgb(229, 234, 236);
                border-radius: 10px;

                
            }
            .p::before{
                position: absolute;
                left: -8px;
                top: 13px;
                content: '';
                border-right: 10px solid rgb(229, 234, 236);
                border-top: 8px solid transparent;
                border-bottom: 8px solid transparent;
            }
        }

        .right{
            width: 100%;
            margin-top: 10px;
            margin-right: 10px;
            justify-content: flex-end;
            display: flex;

            .img{
                
                width: 40px;
                height: 40px;
                object-fit: cover;
                border-radius: 10%;
            }
            .p{
                
                margin-right: 20px;
                font-size: 14px;
                padding: 10px;
                position: relative;
                background: rgb(82, 236, 61);
                border-radius: 10px;

                
            }
            .p::before{
                position: absolute;
                right: -8px;
                top: 13px;
                content: '';
                border-left: 10px solid rgb(82, 236, 61);
                border-top: 8px solid transparent;
                border-bottom: 8px solid transparent;
            }
        }
    }

    .choose{
        float: right;
        margin-top: 10px;
        margin-right:20px ;
    }

  .bottom{
      background: rgb(131, 128, 128);
        display: flex;
        .van-icon{
            margin: 5px;
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
}
</style>