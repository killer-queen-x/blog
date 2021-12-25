<template>
<!-- 我的页面 -->
    <div id="my">

        <div class="nav">
            <van-nav-bar  @click-right="goEdit()">
                
                <template #right>                   
                        <van-icon name="user-circle-o" size="18" />
                </template>
            </van-nav-bar>          
        </div>

        <div v-if="uid" class="user">
            <div class="icon"><img :src="user.icon" alt=""></div>
            <div class="name">
                {{user.nickname}}
                <br><span>{{user.sign}}</span>
            </div>
        </div>
        <div v-else class="user" @click="goLogin()">
            <div class="icon"><img src="https://img2.baidu.com/it/u=1333392363,2961962844&fm=26&fmt=auto" alt=""></div>
            <div class="name">未登录</div>
        </div>

        <div class="box">
            <van-grid :column-num="2">
            <van-grid-item @click="goMyPost()" icon="orders-o" text="我的贴子" />
            <van-grid-item @click="goLivePost()" icon="label-o" text="收藏" />
            <!-- <van-grid-item icon="todo-list-o" text="浏览历史" /> -->
            </van-grid>
        </div>

    

        
    </div>
</template>

<script>
    export default {
        name:'My',
        data(){
            return{
                user:[],
                uid:localStorage.getItem("uid"),
                // icon:localStorage.getItem("icon"),
                // nickname:localStorage.getItem("nickname"),
            }
        },
        created(){
            let uid=localStorage.getItem("uid");
            this.getUser(uid);
        },
        methods:{
            goEdit(){
                this.$router.push({name:'Edit'})
            },
            goLogin(){
                this.$router.push({name:'Login'})
            },
            goMyPost(){
                this.$router.push({name:'MyPost'})
            },
            goLivePost(){
                this.$router.push({name:'LivePost'})
            },
            // 用户信息
            getUser(uid){
                this.axios({
                        method:"get",
                        url:'/GetUser',
                        params:{uid}
                    }).then(res=>{
                        this.user=res.data[0];
                        console.log(res);
                        
                    }).catch(res=>{
                        console.log(404)
                    })
            },
        }
    }
</script>

<style lang="less" scoped>
#my{
  width: 100%;
//   height: 100%;
  background: rgb(240, 236, 236);
  margin-top: -60px;
//   margin-bottom: 60px;
  
  .user{
      margin-top: 0px;
      width: 100%;
      height: 120px;
      display: flex;
      background: white;

      .icon{
          margin: 10px 10px;
        //   width: 60px;
        //   height: 60px;
        // //   border-radius: 50%;
        //   background: rgb(74, 206, 247);
          img{
                width: 60px;
                height: 60px;
                border-radius: 50%;
                object-fit: cover;
          }
      }
      .name{
          margin: 15px 10px;
          font-size:18px ;
          font-weight: 700;
          span{
              font-size: 8px;
              font-weight: 100;
          }
      }
  }

  .box{
      margin: 1px;
  }

}
</style>