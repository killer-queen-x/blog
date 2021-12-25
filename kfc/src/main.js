import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI);
import 'lib-flexible/flexible'

// npm run serve  运行命令行
// 引入vant组件的方式
import { Button,
  NavBar ,
  Tabbar,
  TabbarItem,
  Form,
  Field,
  Icon,
  Toast,
 Popup,
 Search,
 Swipe,
 SwipeItem,
 GoodsAction,
GoodsActionButton,
GoodsActionIcon,
Stepper,
SubmitBar,
Card,
Checkbox,
CheckboxGroup,
Tab,
Tabs,
SwipeCell,
AddressEdit,
AddressList,
Grid,
GridItem,
Badge,
Uploader,
Image as VanImage,
Empty,
Divider,
Tag,
Dialog,
ImagePreview,} from 'vant';
Vue.use(ImagePreview);
Vue.use(Dialog);
Vue.use(Tag);
Vue.use(Divider);
Vue.use(Empty);
Vue.use(VanImage);
Vue.use(Uploader);
Vue.use(Badge);
Vue.use(Grid);
Vue.use(GridItem);
Vue.use(AddressList);
Vue.use(AddressEdit);
Vue.use(SwipeCell);
Vue.use(Tabs);
Vue.use(Tab);
Vue.use(CheckboxGroup);
Vue.use(Checkbox);
Vue.use(Card);
Vue.use(SubmitBar);
Vue.use(GoodsAction);
Vue.use(Stepper);
Vue.use(GoodsActionButton);
Vue.use(GoodsActionIcon);
 Vue.use(Swipe);
Vue.use(SwipeItem);
Vue.use(Search)
Vue.use(Button)
Vue.use(NavBar)
Vue.use(Tabbar)
Vue.use(TabbarItem)
Vue.use(Form)
Vue.use(Field)
Vue.use(Icon)
Vue.use(Toast)
Vue.use(Popup)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
