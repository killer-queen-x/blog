<template>
    <div id="notice">
        <div class="nav">
            <van-nav-bar left-text="返回"  left-arrow @click-left="onClickLeft"   title="通知">
                <template #right>                   
                        <van-icon name="ellipsis" size="18" />
                </template>
            </van-nav-bar>          
        </div>

        
        <div class="tab" >
            <van-tabs v-model="active">
            <van-tab title="评论">
                <div v-if="comnotice.length==0">
                    <van-empty
                        class="custom-image"
                        image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
                        description="暂无评论"
                    />
                </div>
                <div class="comment" v-else v-for="(cnotice,cnid) in comnotice" :key="cnid">
                    <div class="user" @click="goPerson(cnotice.uid)">
                        <img :src="cnotice.cusers[0].icon" alt="">
                        <div class="username">{{cnotice.cusers[0].nickname}}<br><span>{{cnotice.ctime}}</span></div>
                    </div>
                    <div class="content">{{cnotice.comment}}</div>
                    <div class="post" @click="goDetail(cnotice.pid)">
                        <img :src="cnotice.posts[0].image" alt="">
                        <div class="title">{{cnotice.posts[0].title}}</div>
                    </div>
                </div>
            </van-tab>
            <van-tab title="回复">
                <div v-if="replynotice.length==0">
                    <van-empty
                        class="custom-image"
                        image="https://img01.yzcdn.cn/vant/custom-empty-image.png"
                        description="暂无回复"
                    />
                </div>
                <div class="comment" v-else v-for="(rnotice,rnid) in replynotice" :key="rnid">
                    <div class="user" @click="goPerson(rnotice.uid)">
                        <img :src="rnotice.rusers[0].icon" alt="">
                        <div class="username">{{rnotice.rusers[0].nickname}}<br><span>{{rnotice.rtime}}</span></div>
                    </div>
                    <div class="content">{{rnotice.reply}}</div>
                    <div class="post" @click="goDetail(cnotice.pid)">
                        <img :src="rnotice.posts[0].image" alt="">
                        <div class="title">{{rnotice.posts[0].title}}</div>
                    </div>
                </div>
            </van-tab>
            </van-tabs>
        </div>

    </div>
</template>

<script>
    export default {
        name:'Notice',
        data(){
            return{
                active:2,
                comnotice:[],
                replynotice:[],
            }
        },
        created(){
            let uid=localStorage.getItem('uid');
            this.getCommentNotice(uid);
            this.getReplyNotice(uid);
        },
        methods:{
            // 返回
            onClickLeft(){                
                 this.$router.go(-1);
            },
            getCommentNotice(uid){
                this.axios({
                    url:'/CommentNotice',
                    method:'get',
                    params:{uid}
                }).then(res=>{
                    console.log('评论通知')
                    console.log(res)
                    this.comnotice=res.data;
                }).catch(err=>{})
            },
            getReplyNotice(uid){
                this.axios({
                    url:'/ReplyNotice',
                    method:'get',
                    params:{uid}
                }).then(res=>{
                    console.log('回复通知')
                    console.log(res)
                    this.replynotice=res.data
                }).catch(err=>{})
            },
            // 详情
            goDetail(pid){
            this.$router.push({name:'Detail',params:{pid}})
            },
            // 个人
            goPerson(uid){
            this.$router.push({name:'Personal',params:{uid}})
            }
        }
    }
</script>

<style lang="less" scoped>
#notice{
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
      margin-bottom: 50px;
      height: 100%;
      background: rgb(245, 244, 244);
      .comment{
          margin-top: 20px;
          margin-bottom: 20px;
          margin-left: 5%;
          background: rgb(255, 254, 254);
          width: 90%;
          height: 150px;
          border-radius: 10px;
        //   padding: 5px;
          .user{
            //   margin: 5px;
              display: flex;
              img{
                  margin: 5px;
                  width: 40px;
                  height: 40px;
                  object-fit: cover;
                  border-radius: 50%;
              }
              .username{
                  text-align: left;
                  margin-top: 10px;
                  font-size: 14px;
                  font-weight: 700;
                  span{
                      font-size: 5px;
                      font-weight: 300;
                      color: rgb(161, 158, 158);
                  }
              }
          }

          .content{
              margin-left: 10px;
              text-align: left;
              font-size: 16px;
              font-weight: 600;
          }
          .post{
              margin: 5px;
              display: flex;
              border-radius: 10px;
              background: rgb(241, 241, 240);
              img{
                //   margin: 5px;
                  width: 70px;
                  height: 70px;
                  object-fit: cover;
                  border-radius: 10px;
              }
              .title{
                  margin-top: 20px;
              margin-left: 10px;
              text-align: left;
              font-size: 16px;
              font-weight: 600;
              }
          }
      }
  }
}
</style>