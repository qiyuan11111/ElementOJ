(function(c){function e(e){for(var t,n,r=e[0],o=e[1],a=e[2],u=0,s=[];u<r.length;u++)n=r[u],Object.prototype.hasOwnProperty.call(l,n)&&l[n]&&s.push(l[n][0]),l[n]=0;for(t in o)Object.prototype.hasOwnProperty.call(o,t)&&(c[t]=o[t]);f&&f(e);while(s.length)s.shift()();return p.push.apply(p,a||[]),i()}function i(){for(var e,t=0;t<p.length;t++){for(var n=p[t],r=!0,o=1;o<n.length;o++){var a=n[o];0!==l[a]&&(r=!1)}r&&(p.splice(t--,1),e=u(u.s=n[0]))}return e}var n={},l={testregister:0},p=[];function u(e){if(n[e])return n[e].exports;var t=n[e]={i:e,l:!1,exports:{}};return c[e].call(t.exports,t,t.exports,u),t.l=!0,t.exports}u.m=c,u.c=n,u.d=function(e,t,n){u.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(t,e){if(1&e&&(t=u(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)u.d(n,r,function(e){return t[e]}.bind(null,r));return n},u.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(t,"a",t),t},u.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},u.p="/";var t=window["webpackJsonp"]=window["webpackJsonp"]||[],r=t.push.bind(t);t.push=e,t=t.slice();for(var o=0;o<t.length;o++)e(t[o]);var f=r;p.push([4,"chunk-vendors","chunk-common"]),i()})({4:function(e,t,n){e.exports=n("bcf3")},bcf3:function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("a026"),o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("input",{directives:[{name:"model",rawName:"v-model",value:t.account,expression:"account"}],domProps:{value:t.account},on:{input:function(e){e.target.composing||(t.account=e.target.value)}}}),n("input",{directives:[{name:"model",rawName:"v-model",value:t.password,expression:"password"}],domProps:{value:t.password},on:{input:function(e){e.target.composing||(t.password=e.target.value)}}})])},a=[],u={name:"testregister",data:function(){return{account:"",password:""}},methods:{registersubmit:function(){this.$http.post(this.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/account/register",{account:this.account,password:this.password},{emulateJSON:!0}).then(function(e){console.log(e)},function(e){console.log(e)})}}},s=u,c=n("2877"),i=Object(c["a"])(s,o,a,!1,null,"b389a0a2",null),l=i.exports,p=n("8c4f");r["a"].use(p["a"]);var f=[{path:"/testregister",name:"testregister",meta:{title:"client 登录"}}],d=new p["a"]({mode:"history",routes:f}),m=n("a925"),v=n("edad"),g=n("2cd5");n("3495");r["a"].config.productionTip=!1,r["a"].prototype.GLOBAL=v["a"],r["a"].use(m["a"]);var h=new m["a"]({locale:v["a"].OJ_DEFAULT_LANG,messages:g["a"].messages});new r["a"]({i18n:h,router:d,render:function(e){return e(l)}}).$mount("#testregister")}});