<template>
    <div id="edit">
        <div class="nav">
            <van-nav-bar left-text="返回"  left-arrow @click-left="onClickLeft"   title="编辑">
                <template #right>                   
                        <van-icon name="ellipsis" size="18" />
                </template>
            </van-nav-bar>          
        </div>

        <div class="icon">
            <input v-show="false" type="file" accept="image/*" @change="tirggerFile($event)" ref="input" />
            
            <img @click="openImg" :src="user.icon" alt="">
            
        </div>

        <div class="user">
            <van-form @submit="onSubmit">
                <van-field
                    v-model="user.nickname"
                    name="昵称"
                    label="昵称"
                    placeholder="昵称"
                    :rules="[{ required: true, message: '请填写昵称' }]"
                />
                <van-field
                    v-model="user.password"
                    :type="!passwordStatus?'password':'text'"
                    name="密码"
                    label="密码"
                    placeholder="密码"
                    :rules="[{ required: true, message: '请填写密码' }]"
                    :right-icon=" !passwordStatus? 'closed-eye':'eye'"
                    @click-right-icon="passwordStatus=!passwordStatus"
                />
                <van-field
                    v-model="user.sign"
                    name="个性签名"
                    label="个性签名"
                    placeholder="个性签名"
                    :rules="[{ required: true, message: '请填写个性签名' }]"
                />
                <div style="margin: 16px;">
                    <van-button @click="Update()" round block type="info"  native-type="submit">保存</van-button>
                </div>
            </van-form>
        </div>
    </div>
</template>

<script>
    export default {
        name:'Edit',
        data(){
            return{
                
                user:{
                    icon:'',
                    nickname:'',
                    password:'',
                    sign:''
                },
                passwordStatus:false,
            }
        },
        created(){
            let uid=localStorage.getItem("uid");
            this.getUser(uid);
        },
        methods:{
            // 返回
            onClickLeft(){                
                 this.$router.go(-1);
            },
            // 用户信息
            getUser(uid){
                this.axios({
                        method:"get",
                        url:'/GetEdit',
                        params:{uid}
                    }).then(res=>{
                        this.user=res.data[0];
                        console.log(res);
                        
                    }).catch(res=>{
                        console.log(404)
                    })
            },
            onSubmit(values) {
            console.log('submit', values);
            },
            tirggerFile: function(event) {
            let file = event.target.files[0];
            let url = "";
            var reader = new FileReader();
            reader.readAsDataURL(file);
            let that = this;
            reader.onload = function(e) {
                url = this.result.substring(this.result.indexOf(",") + 1);
                that.user.icon = "data:image/png;base64," + url;
                // console.log(that.post)
                // that.$refs['imgimg'].setAttribute('src','data:image/png;base64,'+url);
            };
            },
            openImg() {
            this.$refs.input.click();
            },
            Update(){
                this.axios({
                    url:'/Update',
                    method:'post',
                    data:{
                        uid:localStorage.getItem('uid'),
                        icon:this.user.icon,
                        nickname:this.user.nickname,
                        password:this.user.password,
                        sign:this.user.sign
                    }
                }).then(res=>{
                    console.log(res);
                    if(res.data==true){
                        this.$toast('修改成功');
                        localStorage.setItem('nickname',this.user.nickname);
                        localStorage.setItem('icon',this.user.icon);
                    }else{
                        this.$toast('修改失败')
                    }
                }).catch(err=>{})
            }
        }
    }
</script>

<style lang="less" scoped>
#edit{
    margin-top: -60px;
    margin-bottom: 60px;
    .nav{
    width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
  }
  .icon{
      margin-top:50px ;
    img{
        width: 200px;
        height: 200px;
        object-fit: cover;
        border-radius: 50%;
    }
  }
}
</style>