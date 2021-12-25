<template>
    <div id="login">
        <van-nav-bar title="登录" left-arrow @click-left="onClickLeft"/>
        <div class="logo_img">
            <!-- <img src="../assets/images/mcdonal.jpg" alt="logo">
            <div class="logo_text">肯 德 基</div> -->
        </div>
        <div class="login_from">
            <van-form @submit="onSubmit">
                <van-field v-model="loginUserInfo.username" name="手机号码" label="手机号码" placeholder="手机号码"
                           :rules="[{ required: true, message: '请填写用户名' }]"/>
                <van-field v-model="loginUserInfo.password" :type="!passwordStatus?'password':'text'" name="密码" label="密码"
                        placeholder="密码" :rules="[{ required: true, message: '请填写密码' }]"
                        :right-icon=" !passwordStatus? 'closed-eye':'eye'"
                        @click-right-icon="passwordStatus=!passwordStatus"
                           />
                <div class="regiest">
                    <div>忘记密码</div>
                    <div>|</div>
                    <div @click="showPopup">注册</div>
                </div>
                <div style="margin: 16px;">
                    <van-button @click="login" round block type="info" native-type="submit">登录</van-button>
                </div>

                <div class="regiests">
                    <van-popup v-model="show" round :close-on-click-overlay="false" closeable>
                        <div class="user-regiest">用户注册</div>
                        <div class="popup-reg">
                            <van-field
                                    v-model="regiestUserInfo.username"
                                    name="手机号码"
                                    label="手机号码"
                                    placeholder="手机号码"
                                    :rules="[{ required: true, message: '请填写用户名' }]"
                                    
                            />
                            <van-field
                                    v-model="regiestUserInfo.password"
                                    :type="!repasswordStatus?'password':'text'"
                                    name="密码"
                                    label="密码"
                                    placeholder="密码"
                                    :rules="[{ required: true, message: '请填写密码' }]"
                                    :right-icon=" !repasswordStatus? 'closed-eye':'eye'"
                                    @click-right-icon="repasswordStatus=!repasswordStatus"
                            />
                            
                            <div class="btn">
                                <div style="margin: 16px">
                                    <van-button color="#fc3b3e" round block type="info" @click="check(regiestUserInfo.username)">注册</van-button>
                                </div>
                            </div>
                        </div>
                    </van-popup>
                </div>
            </van-form>
        </div>

    </div>


</template>

<script>
    
    // 导入正则
    import {validForm} from '../assets/js/validForm.js'
    import { Toast } from 'vant';
    import {Icon} from 'vant';
    export default {
        name:'Login',
        data() {
            return {
                loginUserInfo:{
                    username: '',
                    password: '',
                },
                regiestUserInfo:{
                    username: '',
                    password: '',
                    

                },
                show: false,
                passwordStatus:false,
                repasswordStatus:false,
            };
        },
        methods: {
            
            onSubmit(values) {
                console.log('submit', values);
            },
            onClickLeft:function () {
                //点击返回上一页
                this.$router.go(-1)
            },
            //注册弹窗
            showPopup() {
                this.show = true;
            },
            check(username){
                this.axios({
                    url:'Check',
                    method:'get',
                    params:{username}
                }).then(res=>{
                    console.log(res.data.length)
                    if(res.data.length>0){
                        Toast('账号已存在');
                        
                    }else{
                        this.regiester();
                    }
                }).catch(err=>{})
            },
            //注册方法
            regiester(){
                            //构造表单验证信息  注册正则
                let o = {
                    phone: {
                        value: this.regiestUserInfo.username,
                        errorMsg: '手机号格式不正确',
                        reg: /^1[3-9]\d{9}$/
                    },
                    password: {
                        value: this.regiestUserInfo.password,
                        errorMsg: '密码由数字字母下划线组合(6-16字符)',
                        reg: /^[A-Za-z]\w{5,15}$/
                    },
                    
                };

                let isPass = validForm.valid(o);
                console.log(isPass);

                if(!isPass){
                    return
                }
                this.axios({
                    url:'/Register',
                    method:'post',
                    data:{
                        username:this.regiestUserInfo.username,
                        password:this.regiestUserInfo.password,
                        nickname:'游客',
                        icon:'https://img2.baidu.com/it/u=1333392363,2961962844&fm=26&fmt=auto',
                        sign:'这个人什么也没留下'
                    }
                }).then(res=>{
                    console.log(res);
                    if(res.data==true){
                        Toast('注册成功');
                    }else{
                        Toast('注册失败');
                    }
                }).catch(err=>{})
            },
            //登录
            login(){
                //构造表单验证信息  注册正则
                let o = {
                    phone: {
                        value: this.loginUserInfo.username,
                        errorMsg: '手机号格式不正确',
                        reg: /^1[3-9]\d{9}$/
                    },
                    password: {
                        value: this.loginUserInfo.password,
                        errorMsg: '密码由数字字母下划线组合(6-16字符)',
                        reg: /^[A-Za-z]\w{5,15}$/
                    },
                };

                let isPass = validForm.valid(o);
                console.log(isPass);

                if(!isPass){
                    return
                }
                this.axios({
                    method:'get',
                    url:"/Login",
                    params:{
                        username : this.loginUserInfo.username,
                        password : this.loginUserInfo.password,
                    }
                }).then(res=>{
                    console.log('登录的信息:',res);

                    localStorage.setItem('uid',res.data[0].uid);
                    localStorage.setItem('nickname',res.data[0].nickname);
                    localStorage.setItem('icon',res.data[0].icon);

                    
                    this.$router.push({name:'Home'});

                    
                }).catch(err =>{})
            }
            
        },
    };
</script>

<style lang="less" scoped>
 #login{
     /deep/.van-nav-bar .van-icon{
         font-size: 22px;
         color: #ff3b3b;
     }
    /deep/ .van-nav-bar__title{
         color: #ff3b3b;
         font-weight: bold;
         /*font-size: 22px;*/
     }
     .logo_img{
         margin-top: 100px;
         margin-bottom: 50px;
         text-align: center;
         img{
             width: 106px;
             height: 90px;
         }
         .logo_text{
             font-size: 22px;
             color: #AAAAAA;
         }
     }
     .login_from{
         .van-button--info{
             margin-top: 40px;
             background-color: #ff3b3b;
             border:0.02667rem solid #ff3b3b;
         }
         .register{
             background-color: #ffffff;
             border: 1px solid #AAAAAA;
             color: #AAAAAA;
         }
         .regiest{
             display: flex;
             position: absolute;
             right: 10px;
             margin-top: 10px;
             div{
                 margin-right: 5px;
             }
         }
         .user-regiest{
            font-size: 20px;
             padding: 15px;
             font-weight: bold;
         }
         .popup-reg{
             width: 300px;
             height: 300px;
         }


     }

 }
</style>

