<template>
<!-- 发表页面 -->
    <div id="publish">
        <div class="nav">
            <van-nav-bar  left-text="返回" right-text="发布" left-arrow @click-right="publish" @click-left="onClickLeft"/>
        </div>

        <div class="from">
            <div class="title">
                <van-cell-group>
                <van-field 
                v-model="post.title" 
                label="标题" 
                placeholder="请输入标题"
                 />
                </van-cell-group>
            </div>
            <div class="content">
                <textarea style="width:90%;border-radius:10px;"
                v-model="post.content"
                cols="30" 
                rows="10" 
                placeholder="写点什么吧..."
                ></textarea>
            </div>
            

            <div class="upload">
                <input v-show="false" type="file" accept="image/*" @change="tirggerFile($event)" ref="input" />
                <div style="width:40%;height:30%;border:1px none;text-align:center;" @click="openImg">
                <!-- <span v-if="post.image==''">点击选择图片</span> -->
                <van-icon v-if="post.image==''" name="photo-o" size="50"/>
                <img style="height:100%;width:100%;" v-if="post.image!=''" :src="post.image" />
                </div>
            </div>
            
        </div>
    </div>
</template>

<script>
import { Toast } from 'vant';
const unit=require('../assets/js/util');
    export default {
        name:'Publish',
        data(){
            return{
                post:{
                    title:'',
                    content:'',
                    image:'',
                    time:'',
                    nickname:'',
                    uid:'',
                    icon:''
                },
                fileList: [],
                isSelectFile: false,
                imgUrl: ""
            }
        },
        created(){

        },
        methods:{
            tirggerFile: function(event) {
            let file = event.target.files[0];
            let url = "";
            var reader = new FileReader();
            reader.readAsDataURL(file);
            let that = this;
            reader.onload = function(e) {
                url = this.result.substring(this.result.indexOf(",") + 1);
                that.post.image = "data:image/png;base64," + url;
                // console.log(that.post)
                // that.$refs['imgimg'].setAttribute('src','data:image/png;base64,'+url);
            };
            },
            openImg() {
            this.$refs.input.click();
            },

            
            onClickLeft(){
                 this.$router.go(-1)
            },

            publish(){
                if(this.post.title==''){
                    this.$toast('标题不能为空！');
                }else{
                // var that=this;
                console.log(this.post)
                this.axios({
                    method:'post',
                    url:'/Publish',
                    data:{
                        title:this.post.title,
                        content:this.post.content,
                        image:this.post.image,
                        time:unit.formatTime(new Date()),
                        uid:localStorage.getItem("uid")
                    }
                }).then(res=>{
                    console.log(res)
                    if(res.data=='true'){
                        this.$toast('发表成功');

                    }
                    this.$router.push({name:'Home'});
                }).catch(err=>{})
                }
            },
            
            
            
        }
    }
</script>

<style lang="less" scoped>
#publish{
.nav{
    width: 100%;
    position: fixed;
    z-index: 1000;
    top: 0;
  }
  .upload{
      margin-top: 20px;
      margin-left: 20px;
      float: left;
  }
}
</style>