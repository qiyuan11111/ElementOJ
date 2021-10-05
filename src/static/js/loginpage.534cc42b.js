(function(c){function t(t){for(var e,o,s=t[0],r=t[1],n=t[2],i=0,a=[];i<s.length;i++)o=s[i],Object.prototype.hasOwnProperty.call(u,o)&&u[o]&&a.push(u[o][0]),u[o]=0;for(e in r)Object.prototype.hasOwnProperty.call(r,e)&&(c[e]=r[e]);m&&m(t);while(a.length)a.shift()();return d.push.apply(d,n||[]),l()}function l(){for(var t,e=0;e<d.length;e++){for(var o=d[e],s=!0,r=1;r<o.length;r++){var n=o[r];0!==u[n]&&(s=!1)}s&&(d.splice(e--,1),t=i(i.s=o[0]))}return t}var o={},u={loginpage:0},d=[];function i(t){if(o[t])return o[t].exports;var e=o[t]={i:t,l:!1,exports:{}};return c[t].call(e.exports,e,e.exports,i),e.l=!0,e.exports}i.m=c,i.c=o,i.d=function(t,e,o){i.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:o})},i.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},i.t=function(e,t){if(1&t&&(e=i(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var o=Object.create(null);if(i.r(o),Object.defineProperty(o,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var s in e)i.d(o,s,function(t){return e[t]}.bind(null,s));return o},i.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return i.d(e,"a",e),e},i.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},i.p="/";var e=window["webpackJsonp"]=window["webpackJsonp"]||[],s=e.push.bind(e);e.push=t,e=e.slice();for(var r=0;r<e.length;r++)t(e[r]);var m=s;d.push([2,"chunk-vendors","chunk-common"]),l()})({2:function(t,e,o){t.exports=o("bce3")},"5b36":function(t,e,o){},"9e07":function(t,e,o){"use strict";var s=o("5b36"),r=o.n(s);r.a},bce3:function(t,e,o){"use strict";o.r(e);o("e260"),o("e6cf"),o("cca6"),o("a79d");var s=o("a026"),r=function(){var i=this,t=i.$createElement,e=i._self._c||t;return e("div",{attrs:{id:"loginpage"}},[e("loadings",{attrs:{isLoading:i.settings.base.beforeLoad?1:0}}),i.settings.base.beforeLoad?i._e():e("div",{staticStyle:{display:"flex","flex-direction":"column","min-height":"100vh"}},[e("NavBar",{attrs:{isActive:i.settings.base.belong,isLogin:i.settings.base.isLogin,user:i.settings.base.userInfo}}),e("div",{staticClass:"container mundb-standard-container"},[e("div",{staticClass:"row justify-content-sm-center"},[e("div",{staticClass:"col-sm-12 col-md-8 col-lg-6"},[i._m(0),e("div",{staticClass:"card"},[e("div",{staticClass:"card-header"},[e("ul",{staticClass:"nav nav-tabs card-header-tabs nav-justified nav-tabs-material",attrs:{id:"accountTab",role:"tablist"}},[e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link active",attrs:{id:"login-tab",href:"javascript:void(0)",role:"tab"}},[i._v(i._s(i.$t("DEFAULT.OJ_MESSAGE_LOGIN")))])]),e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link ",attrs:{id:"register-tab",href:"registerpage.html",role:"tab"}},[i._v(i._s(i.$t("DEFAULT.OJ_MESSAGE_REGISTER")))])]),e("div",{staticClass:"nav-tabs-indicator",attrs:{id:"nav-tabs-indicator"}})])]),e("div",{staticClass:"tab-content",attrs:{id:"accountTabContent"}},[e("div",{staticClass:"tab-pane fade show active",attrs:{role:"tabpanel"}},[e("form",{staticClass:"needs-validation",attrs:{id:"login_form"},on:{submit:function(t){return t.preventDefault(),i.postLoginForm()}}},[e("div",{staticClass:"card-body"},[e("div",{staticClass:"form-group bmd-form-group",class:{"is-filled":""!=i.forms.loginForm.account,"is-focused":"account"==i.settings.loginForm.currentFocus&&i.settings.loginForm.hasFocus}},[e("label",{staticClass:"bmd-label-floating",attrs:{for:"account"}},[i._v(i._s(i.$t("DEFAULT.OJ_MESSAGE_ACCOUNT")))]),e("input",{directives:[{name:"model",rawName:"v-model",value:i.forms.loginForm.account,expression:"forms.loginForm.account"}],staticClass:"form-control",attrs:{type:"text",name:"account",id:"account",required:""},domProps:{value:i.forms.loginForm.account},on:{focus:function(t){return i.isLoginFormFocus("account")},blur:function(t){i.isLoginFormBlur(),i.checkAccountData()},keyup:function(t){return i.checkAccountData()},input:function(t){t.target.composing||i.$set(i.forms.loginForm,"account",t.target.value)}}}),e("span",{staticClass:"invalid-feedback",style:{display:"inline-block",float:"right",color:i.result.loginForm.errorMessage.account==i.$t("WARN.OJ_MESSAGE_ACCEPTABLE")?"green":"",visibility:i.settings.loginForm.hasFocus&&"account"==i.settings.loginForm.currentFocus||i.forms.loginForm.account.length>0?"":"hidden"}},[e("strong",[i._v(i._s(i.result.loginForm.errorMessage.account))])]),e("div",{staticStyle:{clear:"both"}})]),e("div",{staticClass:"form-group bmd-form-group",class:{"is-filled":""!=i.forms.loginForm.password,"is-focused":"password"==i.settings.loginForm.currentFocus&&i.settings.loginForm.hasFocus}},[e("label",{staticClass:"bmd-label-floating",attrs:{for:"password"}},[i._v(i._s(i.$t("DEFAULT.OJ_MESSAGE_PASSWORD")))]),e("input",{directives:[{name:"model",rawName:"v-model",value:i.forms.loginForm.password,expression:"forms.loginForm.password"}],staticClass:"form-control",attrs:{type:"password",id:"password",autocomplete:"off",required:"required"},domProps:{value:i.forms.loginForm.password},on:{focus:function(t){return i.isLoginFormFocus("password")},blur:function(t){i.isLoginFormBlur(),i.checkPasswordData()},keyup:function(t){return i.checkPasswordData()},input:function(t){t.target.composing||i.$set(i.forms.loginForm,"password",t.target.value)}}}),e("span",{staticClass:"invalid-feedback",style:{display:"inline-block",float:"right",color:i.result.loginForm.errorMessage.password==i.$t("WARN.OJ_MESSAGE_ACCEPTABLE")?"green":"",visibility:i.settings.loginForm.hasFocus&&"password"==i.settings.loginForm.currentFocus||i.forms.loginForm.password.length>0?"":"hidden"}},[e("strong",[i._v(i._s(i.result.loginForm.errorMessage.password))])]),e("div",{staticStyle:{clear:"both"}})]),1==i.settings.hasVcode?[e("div",{staticClass:"form-group bmd-form-group",class:{"is-filled":""!=i.forms.inputs.vcode,"is-focused":"vcode"==i.settings.loginForm.currentFocus&&i.settings.loginForm.hasFocus},staticStyle:{float:"left",width:"65%"}},[e("label",{staticClass:"bmd-label-floating",attrs:{for:"vcode"}},[i._v(i._s(i.$t("DEFAULT.OJ_MESSAGE_VERIFICATION_CODE")))]),e("input",{directives:[{name:"model",rawName:"v-model",value:i.forms.inputs.vcode,expression:"forms.inputs.vcode"}],staticClass:"form-control",attrs:{type:"password",id:"vcode",autocomplete:"off",required:"required"},domProps:{value:i.forms.inputs.vcode},on:{focus:function(t){return i.isLoginFormFocus("vcode")},blur:function(t){i.isLoginFormBlur(),i.checkVcodeData()},keyup:function(t){return i.checkVcodeData()},input:function(t){t.target.composing||i.$set(i.forms.inputs,"vcode",t.target.value)}}}),e("span",{staticClass:"invalid-feedback",style:{display:"inline-block",float:"right",color:i.result.loginForm.errorMessage.vcode==i.$t("WARN.OJ_MESSAGE_ACCEPTABLE")?"green":"",visibility:i.settings.loginForm.hasFocus&&"vcode"==i.settings.loginForm.currentFocus||i.forms.inputs.vcode.length>0?"":"hidden"}},[e("strong",[i._v(i._s(i.result.loginForm.errorMessage.vcode))])]),e("div",{staticStyle:{clear:"both"}})]),e("div",{staticStyle:{float:"right",width:"30%","margin-top":"21px","margin-right":"10px"}},[e("img",{ref:"vcode_img",staticStyle:{cursor:"pointer"},on:{click:function(t){return i.getVcodeImage()}}})]),e("div",{staticStyle:{clear:"both"}})]:i._e(),e("div",{staticClass:"form-group bmd-form-group"},[e("div",{staticClass:"checkbox"},[e("label",{staticClass:"is-filled",attrs:{for:"remember"}},[e("input",{directives:[{name:"model",rawName:"v-model",value:i.forms.loginForm.rememberMe,expression:"forms.loginForm.rememberMe"}],staticClass:"form-control",attrs:{type:"checkbox",name:"remember",id:"remember",value:"rememberme"},domProps:{checked:Array.isArray(i.forms.loginForm.rememberMe)?i._i(i.forms.loginForm.rememberMe,"rememberme")>-1:i.forms.loginForm.rememberMe},on:{change:function(t){var e=i.forms.loginForm.rememberMe,o=t.target,s=!!o.checked;if(Array.isArray(e)){var r="rememberme",n=i._i(e,r);o.checked?n<0&&i.$set(i.forms.loginForm,"rememberMe",e.concat([r])):n>-1&&i.$set(i.forms.loginForm,"rememberMe",e.slice(0,n).concat(e.slice(n+1)))}else i.$set(i.forms.loginForm,"rememberMe",s)}}}),i._m(1),e("span",[i._v(i._s(i.$t("DEFAULT.OJ_MESSAGE_REMEMBER_ME")))])])])])],2),e("div",{staticClass:"card-footer text-right"},[e("a",{attrs:{href:"javascript:void(0)"}},[e("button",{staticClass:"btn btn-secondary",attrs:{type:"button"}},[i._v(" "+i._s(i.$t("DEFAULT.OJ_MESSAGE_FORGET_PASSWORD"))+" ")])]),e("button",{staticClass:"btn btn-danger",attrs:{type:"submit"}},[i._v(" "+i._s(i.$t("DEFAULT.OJ_MESSAGE_LOGIN"))+" ")])])])])])])])])]),1==i.result.loginForm.modelOpen?[e("div",{staticClass:"modal fade",attrs:{id:"login_error",tabindex:"-1",role:"dialog","aria-labelledby":"login_error_title","aria-hidden":"true"}},[e("div",{staticClass:"modal-dialog modal-dialog-centered",attrs:{role:"document"}},[e("div",{staticClass:"modal-content"},[e("div",{staticClass:"modal-body"},[e("h3",{staticStyle:{color:"#ff3f4c"}},[i._v(" "+i._s(i.$t("WARN.OJ_MESSAGE_UNSUCCESSFULLY_LOGIN"))+" ")]),e("br"),e("p",[i._v(i._s(i.result.loginForm.loginInfo))])]),e("div",{staticClass:"modal-footer"},[e("button",{staticClass:"btn btn-secondary",attrs:{type:"button","data-dismiss":"modal"},on:{click:function(t){return i.closeLoginInfoModel()}}},[i._v(" OK ")])])])])])]:i._e(),e("footers")],2)],1)},n=[function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"text-center",staticStyle:{"margin-top":"10vh","margin-bottom":"20px"}},[o("h1",{staticStyle:{padding:"20px",display:"inline-block"}},[t._v("Element OJ")]),o("p",[t._v("An Onlinejudger for Ctguacm")])])},function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("span",{staticClass:"checkbox-decorator"},[o("span",{staticClass:"check"})])}],i=o("fb44"),a=i["a"],c=(o("9e07"),o("2877")),l=Object(c["a"])(a,r,n,!1,null,"9a08544a",null),u=l.exports,d=o("54a1"),m=o("8c4f");s["a"].use(m["a"]);var g=[{path:"/loginpage",name:"loginpage",meta:{title:"client 登录"}}],f=new m["a"]({mode:"history",routes:g}),_=o("a925"),p=o("edad"),E=o("2cd5");o("3495");s["a"].config.productionTip=!1,s["a"].prototype.GLOBAL=p["a"],s["a"].use(_["a"]);var v=new _["a"]({locale:p["a"].OJ_DEFAULT_LANG,messages:E["a"].messages});new s["a"]({i18n:v,router:f,render:function(t){return t(u)}}).$mount("#loginpage"),new s["a"]({i18n:v,router:f,render:function(t){return t(d["a"])}}).$mount("#loadings")},fb44:function(t,n,i){"use strict";(function(c){i("13d5c"),i("a9e3"),i("d3b7"),i("5cc6"),i("9a8c"),i("a975"),i("735e"),i("c1ac"),i("d139"),i("3a7b"),i("d5d6"),i("82f8"),i("e91f"),i("60bd"),i("5f96"),i("3280"),i("3fcc"),i("ca91"),i("25a1"),i("cd26"),i("3c5d"),i("2954"),i("649e"),i("219c"),i("170b"),i("b39a"),i("72f7"),i("96cf");var o=i("1da1"),t=i("2ba1"),e=i("ff6f"),l=i("2cd5"),s=i("2c4b"),r=i("54a1");n["a"]={name:"loginpage",components:{Footers:s["a"],NavBar:e["a"],Loadings:r["a"]},data:function(){return{settings:{base:{belong:"account",beforeLoad:!0,isLogin:!1,user:new Object},loginForm:{currentFocus:"",hasFocus:!1},hasVcode:!1},forms:{loginForm:{account:"",password:"",rememberMe:"",vcode:"",csrf:""}},result:{loginForm:{isLogining:!1,hasError:!1,loginInfo:"",errorMessage:{account:"　l",password:"　",vcode:"　"},modelOpen:!1}}}},methods:{submit_img:function(){this.$refs.imgs.click()},postLoginForm:function(){this.result.loginForm.modelOpen=!1,this.result.loginForm.loginInfo="",this.result.loginForm.isLogining=!0,this.result.loginForm.hasError=!1,this.forms.loginForm.password=Object(t["a"])(this.forms.loginForm.password);var a=this;console.log(a.forms.loginForm),window.axios.post(a.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/account/login",l["a"].stringify(a.forms.loginForm)).then(function(t){var e=t.data;a.result.loginForm.isLogining=!1;var o=e.OJ_ERROR_TYPE;if(o!=a.GLOBAL.OJ_ERROR_NO_ERROR){a.result.loginForm.hasError=!0;var s=Number(e.OJ_LOGIN_ERROR_TIMES);s>=a.GLOBAL.OJ_DEFAULT_NEED_VCODE_TIMES&&a.getVcodeImage(),a.forms.loginForm.password=""}o==a.GLOBAL.OJ_ERROR_IP_BE_BANNED?a.result.loginForm.loginInfo=a.$t("WARN.OJ_MESSAGE_IP_BE_BANNED"):o==a.GLOBAL.OJ_ERROR_WRONG_VCODE?(a.forms.inputs.vcode="",c("#vcode").focus(),a.result.loginForm.errorMessage.vcode=a.$t("WARN.OJ_MESSAGE_WRONG_VCODE"),a.getVcodeImage()):o==a.GLOBAL.OJ_ERROR_ACCOUNT_BE_BANNED?a.result.loginForm.loginInfo=a.$t("WARN.OJ_MESSAGE_ACCOUNT_BE_BANNED"):o==a.GLOBAL.OJ_ERROR_NON_EXISTENT_ACCOUNT?(c("#account").focus(),a.result.loginForm.errorMessage.account=a.$t("WARN.OJ_MESSAGE_NON_EXISTENT_ACCOUNT"),document.getElementById("account").setCustomValidity(a.$t("WARN.OJ_MESSAGE_NON_EXISTENT_ACCOUNT"))):o==a.GLOBAL.OJ_ERROR_REPETED_ACCOUNT?(c("#account").focus(),a.result.loginForm.errorMessage.account=a.$t("WARN.OJ_MESSAGE_REPETED_ACCOUNT"),document.getElementById("account").setCustomValidity(a.$t("WARN.OJ_MESSAGE_REPETED_ACCOUNT"))):o==a.GLOBAL.OJ_ERROR_ACCOUNT_BE_BANNED?a.result.loginForm.loginInfo=a.$t("WARN.OJ_MESSAGE_ACCOUNT_BE_BANNED"):o==a.GLOBAL.OJ_ERROR_WRONG_PASSWORD?(c("#password").focus(),a.result.loginForm.errorMessage.password=a.$t("WARN.OJ_MESSAGE_WRONG_PASSWORD"),document.getElementById("password").setCustomValidity(a.$t("WARN.OJ_MESSAGE_WRONG_PASSWORD"))):o!=a.GLOBAL.OJ_ERROR_NO_ERROR&&(a.result.loginForm.loginInfo=a.$t("WARN.OJ_MESSAGE_BACK_JUMP")),a.result.loginForm.loginInfo.length>0&&(a.result.loginForm.modelOpen=!0,setTimeout(function(){c("#login_error").modal({backdrop:"static"})},100));var r=e.OJ_USER_INFO.saved;if(0==a.result.loginForm.hasError){var n=e.OJ_ACCESS_TOKEN,i=e.OJ_REFRESH_TOKEN;r?(l["a"].setCookieWithTime("AccessToken",n,.025),l["a"].setCookieWithTime("RefreshToken",i,7)):(l["a"].setCookie("AccessToken",n),l["a"].setCookie("RefreshToken",i)),window.history.go(-1)}}).catch(function(t){console.log(t)})},isLoginFormFocus:function(t){this.settings.loginForm.currentFocus=t,this.settings.loginForm.hasFocus=!0},isLoginFormBlur:function(){this.settings.loginForm.hasFocus=!1},checkAccountData:function(){var t=document.getElementById("account");this.result.loginForm.errorMessage.account="　",t.setCustomValidity("")},checkPasswordData:function(){var t=document.getElementById("password");this.result.loginForm.errorMessage.password="　",t.setCustomValidity("")},checkVcodeData:function(){var t=document.getElementById("vcode");this.result.loginForm.errorMessage.vcode="　",t.setCustomValidity("")},getVcodeImage:function(){this.settings.hasVcode=!0,this.$http.post(this.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/vcode",{},{responseType:"arraybuffer"}).then(function(t){var e="data:image/png;base64,"+btoa(new Uint8Array(t.data).reduce(function(t,e){return t+String.fromCharCode(e)},""));this.$refs.vcode_img.src=e},function(){console.log("请求失败处理")})},closeLoginInfoModel:function(){var t=this;setTimeout(function(){t.result.loginForm.modelOpen=!1,t.result.loginForm.loginInfo=""},300)},identify:function(){var t=Object(o["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,l["a"].idenfity();case 2:return e=t.sent,this.$i18n.locale=e.OJ_LANGUAGE_TYPE,e.OJ_ERROR_TYPE==this.GLOBAL.OJ_ERROR_NO_ERROR&&(this.settings.base.isLogin=!0,this.settings.base.userInfo=e.OJ_USER_INFO),t.abrupt("return",e);case 6:case"end":return t.stop()}},t,this)}));function e(){return t.apply(this,arguments)}return e}(),searchLoginSettings:function(){var t=this;return window.axios.post(t.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/account/login/settings",{},{headers:{OJ_ACCESS_TOKEN:l["a"].getCookie("AccessToken")}})}},created:function(){var r=this;return Object(o["a"])(regeneratorRuntime.mark(function t(){var e,s;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return r.settings.hasVcode=!1,t.next=3,r.identify();case 3:if(e=t.sent,e.OJ_ERROR_TYPE!=r.GLOBAL.OJ_ERROR_NO_ERROR){t.next=7;break}return window.history.go(-1),t.abrupt("return");case 7:s=r,window.axios.all([s.searchLoginSettings()]).then(window.axios.spread(function(t){var e=t.data;s.forms.loginForm.csrf=e.OJ_LOGIN_CSRF;var o=Number(e.OJ_LOGIN_ERROR_TIMES);o>=r.GLOBAL.OJ_DEFAULT_NEED_VCODE_TIMES&&s.getVcodeImage(),s.settings.base.beforeLoad=!1})).catch(function(t){console.log(t)});case 9:case"end":return t.stop()}},t)}))()}}}).call(this,i("1157"))}});