(function(t){function e(e){for(var a,i,n=e[0],l=e[1],c=e[2],m=0,d=[];m<n.length;m++)i=n[m],Object.prototype.hasOwnProperty.call(s,i)&&s[i]&&d.push(s[i][0]),s[i]=0;for(a in l)Object.prototype.hasOwnProperty.call(l,a)&&(t[a]=l[a]);p&&p(e);while(d.length)d.shift()();return o.push.apply(o,c||[]),r()}function r(){for(var t,e=0;e<o.length;e++){for(var r=o[e],a=!0,n=1;n<r.length;n++){var l=r[n];0!==s[l]&&(a=!1)}a&&(o.splice(e--,1),t=i(i.s=r[0]))}return t}var a={},s={problem:0},o=[];function i(e){if(a[e])return a[e].exports;var r=a[e]={i:e,l:!1,exports:{}};return t[e].call(r.exports,r,r.exports,i),r.l=!0,r.exports}i.m=t,i.c=a,i.d=function(t,e,r){i.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:r})},i.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},i.t=function(t,e){if(1&e&&(t=i(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var r=Object.create(null);if(i.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var a in t)i.d(r,a,function(e){return t[e]}.bind(null,a));return r},i.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return i.d(e,"a",e),e},i.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},i.p="";var n=window["webpackJsonp"]=window["webpackJsonp"]||[],l=n.push.bind(n);n.push=e,n=n.slice();for(var c=0;c<n.length;c++)e(n[c]);var p=l;o.push([7,"chunk-vendors","chunk-common"]),r()})({"541f":function(t,e,r){"use strict";var a=r("6f78"),s=r.n(a);s.a},"6f5f":function(t,e,r){"use strict";(function(t){r("96cf");var a=r("1da1"),s=r("ff6f"),o=r("2cd5"),i=r("2c4b"),n=r("54a1"),l=r("b311"),c=r.n(l),p=r("a026");e["a"]={components:{Loadings:n["a"],Footers:i["a"],NavBar:s["a"]},name:"problem",data:function(){return{settings:{base:{belong:"problemset",beforeLoad:!0,isLogin:!1,user:new Object},problem:{pid:0,copy:{id:"",ing:!1,type:""}}},forms:{problemForm:{pid:0}},result:{problemForm:{problem:new Object}}}},methods:{formatTime:function(t){return o["a"].formatTime(t)},handleCopy:function(t,e,r,a){o["a"].handleClipboard(t,e),this.settings.problem.copy.id=r,this.settings.problem.copy.type=a,this.settings.problem.copy.ing=!0;var s=this;setTimeout((function(){s.settings.problem.copy.ing=!1}),500)},copyLink:function(t){console.log(1);var e=new c.a(".tag");e.on("success",(function(t){alert(2),e.destroy()})),e.on("error",(function(t){alert(5),e.destroy()}))},randomColor:function(){var t=new Array("#F44336","#ff9800","#4CAF50","#8bc34a","#00bcd4","#2196F3","#9c27b0","#3f51b5","#009688","#e91e63"),e=Math.floor(Math.random()*t.length);return t[e]},refresh:function(){var t=this;return Object(a["a"])(regeneratorRuntime.mark((function e(){var r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:r=t,setTimeout(Object(a["a"])(regeneratorRuntime.mark((function t(){var e;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,o["a"].refresh();case 2:e=t.sent,e.OJ_ERROR_TYPE==r.GLOBAL.OJ_ERROR_NO_ERROR?(r.settings.base.isLogin=!0,r.settings.base.userInfo=e.OJ_USER_INFO):window.location.href="/loginpage.html",r.refresh();case 5:case"end":return t.stop()}}),t)}))),12e5);case 2:case"end":return e.stop()}}),e)})))()},identify:function(){var t=Object(a["a"])(regeneratorRuntime.mark((function t(){var e;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,o["a"].idenfity();case 2:return e=t.sent,this.$i18n.locale=e.OJ_LANGUAGE_TYPE,e.OJ_ERROR_TYPE==this.GLOBAL.OJ_ERROR_NO_ERROR&&(this.settings.base.isLogin=!0,this.settings.base.userInfo=e.OJ_USER_INFO),this.refresh(),t.abrupt("return",e);case 7:case"end":return t.stop()}}),t,this)})));function e(){return t.apply(this,arguments)}return e}(),searchProblem:function(){var t=this;return window.axios.post(t.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/problem/get",o["a"].stringify(t.forms.problemForm),{headers:{OJ_ACCESS_TOKEN:o["a"].getCookie("AccessToken")}})}},created:function(){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function r(){var a;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:return r.next=2,e.identify();case 2:e.forms.problemForm.pid=e.settings.problem.pid=o["a"].getUrlKey("pid"),null==e.forms.problemForm.pid&&(window.location.href="/problemset.html"),a=e,window.axios.all([a.searchProblem()]).then(window.axios.spread((function(r){var s=r.data;a.result.problemForm.problem=s.OJ_PROBLEM;var o=s.OJ_ERROR_TYPE;if(o==a.GLOBAL.OJ_ERROR_NO_ERROR||o==a.GLOBAL.OJ_ERROR_NOT_LOGIN){e.settings.base.beforeLoad=!1;for(var i=0;i<a.result.problemForm.problem.category.length;i++)p["a"].set(a.result.problemForm.problem.category[i],"color",a.randomColor());for(var n=0;n<a.result.problemForm.problem.source.length;n++)p["a"].set(a.result.problemForm.problem.source[n],"color",a.randomColor())}else o==a.GLOBAL.OJ_ERROR_NON_EXISTENT_PROBLEM&&(window.location.href="/problemset.html");document.title=e.result.problemForm.problem.title+" | CTGUACM",t(document).ready((function(){t(".double-card").each((function(){var e,r=t(this).children(".card:first").children(".card-body").height(),a=t(this).children(".card:last").children(".card-body").height();e=r>a?r:a,t(this).children(".card:first").children(".card-body").css("height",e+25+"px"),t(this).children(".card:last").children(".card-body").css("height",e+25+"px")}))}))}))).catch((function(t){console.log(t)}));case 6:case"end":return r.stop()}}),r)})))()}}}).call(this,r("1157"))},"6f78":function(t,e,r){},7:function(t,e,r){t.exports=r("a867")},a867:function(t,e,r){"use strict";r.r(e);r("e260"),r("e6cf"),r("cca6"),r("a79d");var a=r("a026"),s=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{attrs:{id:"problem"}},[r("loadings",{attrs:{isLoading:t.settings.base.beforeLoad?1:0}}),t.settings.base.beforeLoad?t._e():r("div",{staticStyle:{"overflow-x":"hidden"}},[r("NavBar",{attrs:{isActive:t.settings.base.belong,isLogin:t.settings.base.isLogin,user:t.settings.base.userInfo}}),r("div",{staticClass:"container mundb-standard-container"},[r("div",{staticClass:"row"},[r("div",{staticClass:"col-sm-12 col-lg-9"},[r("paper-card",{staticClass:"animated fadeInLeft p-5",staticStyle:{"min-height":"643px"}},[r("div",{staticStyle:{"font-family":"'Consolas'","white-space":"pre"}},[r("div",{staticStyle:{"text-align":"center"}},[r("h3",[t._v(t._s(t.result.problemForm.problem.problemId+": "+t.result.problemForm.problem.title))])]),r("div",{staticStyle:{"text-align":"center","margin-top":"10px"}},[r("span",{attrs:{"data-placement":"left","data-toggle":"tooltip","data-original-title":"Time Limit"}},[r("i",{staticClass:"fa fa-clock-o",attrs:{"aria-hidden":"true"}}),r("span",[r("span",{attrs:{fd:"time_limit",pid:""}},[t._v(t._s(" ")+t._s(t.result.problemForm.problem.timeLimit)+" ms"+t._s(" ")+t._s(" ")+t._s(" ")+t._s(" "))])])]),r("span",{attrs:{"data-placement":"left","data-toggle":"tooltip","data-original-title":"Memory Limit"}},[r("i",{staticClass:"fa fa-cog",attrs:{"aria-hidden":"true"}}),r("span",[t._v(t._s(" ")+t._s((t.result.problemForm.problem.memoryLimit/1024).toFixed(0))+" KB"+t._s(" ")+t._s(" ")+t._s(" ")+t._s(" "))])]),r("span",[r("i",{staticClass:"fa fa-sign-out",attrs:{"aria-hidden":"true"}}),r("span",[t._v(" Standard I/O")])])]),t.result.problemForm.problem.isSpj?r("div",{staticStyle:{"text-align":"center",color:"red","margin-top":"10px"}},[r("span",[t._v("Special Judge")])]):t._e()]),r("div",{staticStyle:{"margin-top":"1rem","font-size":"17px"}},[r("i",{staticClass:"fa fa-bars",attrs:{"aria-hidden":"true"}}),r("span",[t._v(" Problem Information")])]),r("div",{staticClass:"problem"},[null!=t.result.problemForm.problem.problemDescribe&&""!=t.result.problemForm.problem.problemDescribe?r("div",{staticClass:"card"},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[t._v("Problem Description")])]),r("div",{staticClass:"card-body"},[r("span",{staticStyle:{"white-space":"pre"},domProps:{innerHTML:t._s(t.result.problemForm.problem.problemDescribe)}})])]):t._e(),null!=t.result.problemForm.problem.inputDescribe&&""!=t.result.problemForm.problem.inputDescribe?r("div",{staticClass:"card"},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[t._v("Input Description")])]),r("div",{staticClass:"card-body"},[r("span",{staticStyle:{"white-space":"pre"},domProps:{innerHTML:t._s(t.result.problemForm.problem.inputDescribe)}})])]):t._e(),null!=t.result.problemForm.problem.outputDescribe&&""!=t.result.problemForm.problem.outputDescribe?r("div",{staticClass:"card"},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[t._v("Output Description")])]),r("div",{staticClass:"card-body"},[r("span",{staticStyle:{"white-space":"pre"},domProps:{innerHTML:t._s(t.result.problemForm.problem.outputDescribe)}})])]):t._e(),t._l(t.result.problemForm.problem.sampleIO,(function(e,a){return[r("div",{key:t.key,staticClass:"double-card"},[r("div",{staticClass:"card",staticStyle:{width:"48%",float:"left"}},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[t._v(" Sample Input "+t._s(a+1)+" "),t.settings.problem.copy.id==a&&1==t.settings.problem.copy.ing&&0==t.settings.problem.copy.type?r("a",{attrs:{href:"javascript:void(0)"}},[r("i",{staticClass:"fa fa-check-circle",attrs:{"aria-hidden":"true"}})]):r("a",{attrs:{href:"javascript:void(0)"},on:{click:function(r){return t.handleCopy(e.sample_I,r,a,0)}}},[r("i",{staticClass:"fa fa-files-o",attrs:{"aria-hidden":"true"}})])])]),r("div",{staticClass:"card-body"},[r("span",{staticClass:"sampledata",staticStyle:{"white-space":"pre"},attrs:{id:"sampleinput"+a}},[t._v(t._s(e.sample_I))])])]),r("div",{staticClass:"card",staticStyle:{width:"48%",float:"right"}},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[t._v(" Sample Output "+t._s(a+1)+" "),t.settings.problem.copy.id==a&&1==t.settings.problem.copy.ing&&1==t.settings.problem.copy.type?r("a",{attrs:{href:"javascript:void(0)"}},[r("i",{staticClass:"fa fa-check-circle",attrs:{"aria-hidden":"true"}})]):r("a",{attrs:{href:"javascript:void(0)"},on:{click:function(r){return t.handleCopy(e.sample_O,r,a,1)}}},[r("i",{staticClass:"fa fa-files-o",attrs:{"aria-hidden":"true"}})])])]),r("div",{staticClass:"card-body"},[r("span",{staticClass:"sampledata",staticStyle:{"white-space":"pre"},attrs:{id:"sampleoutput"+a}},[t._v(t._s(e.sample_O))])])]),r("div",{staticStyle:{clear:"both"}})])]})),null!=t.result.problemForm.problem.hint&&""!=t.result.problemForm.problem.hint?r("div",{staticClass:"card"},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[t._v("Hint")])]),r("div",{staticClass:"card-body"},[r("span",{staticStyle:{"white-space":"pre"},domProps:{innerHTML:t._s(t.result.problemForm.problem.hint)}})])]):t._e()],2)])],1),r("div",{staticClass:"col-sm-12 col-lg-3 no-print"},[r("paper-card",{staticClass:"animated fadeInRight btn-group-vertical cm-action-group"},[r("button",{staticClass:"btn btn-secondary submit-code",attrs:{type:"button",onclick:"window.location.href='/submitpage.html?pid="+t.result.problemForm.problem.problemId+"'"}},[r("i",{staticClass:"fa fa-paper-plane fa-fw",attrs:{"aria-hidden":"true"}}),0==t.settings.base.isLogin?[t._v(" login & submit ")]:[t._v(" submit ")]],2),r("separate-line",{staticClass:"ultra-thin"}),r("button",{staticClass:"btn btn-secondary submit-info",attrs:{type:"button",onclick:"window.location.href='/status.html?pid="+t.result.problemForm.problem.problemId+"'"}},[r("i",{staticClass:"fa fa-check-square fa-fw",attrs:{"aria-hidden":"true"}}),t._v(" submited info ")]),r("button",{staticClass:"btn btn-secondary data",attrs:{type:"button"}},[r("i",{staticClass:"fa fa-code fa-fw",attrs:{"aria-hidden":"true"}}),t._v(" source data ")])],1),r("paper-card",{staticClass:"animated fadeInRight",attrs:{id:"RightInfoPanel"}},[r("p",[r("span",[t._v("Info"+t._s(" ")+t._s(" "))])]),r("div",[r("a",{attrs:{href:"javascript:void(0)",target:"_blank"}},[r("img",{staticClass:"img-fluid mb-3",attrs:{src:"https://oj.ctguacm.work/image/ojlogo.png",alt:"CTGUACMOJ"}})])]),r("p",[r("span",{attrs:{s:""}},[t._v("Code"+t._s(" ")+t._s(" "))]),r("span",{staticClass:"wemd-black-text"},[t._v("CTGAOJ"+t._s(t.result.problemForm.problem.problemId))])]),r("p",{staticClass:"mb-0"},[r("span",[t._v("Provider"+t._s(" ")+t._s(" "))])]),r("div",{attrs:{id:"ProblemCategory"}},t._l(t.result.problemForm.problem.category,(function(e){return r("a",{key:t.key,style:{color:e.color,"border-color":e.color},attrs:{href:"/problemset.html?category="+encodeURIComponent(e.cateId)}},[t._v(" "+t._s(e.name)+" ")])})),0),r("separate-line",{staticClass:"ultra-thin mb-3 mt-3"}),r("p",{staticClass:"mb-0"},[r("span",[t._v("Tags"+t._s(" ")+t._s(" "))])]),r("div",{attrs:{id:"ProblemSource"}},t._l(t.result.problemForm.problem.source,(function(e){return r("a",{key:t.key,style:{color:e.color,"border-color":e.color},attrs:{href:"/problemset.html?source="+encodeURIComponent(e.sourceId)}},[t._v(" "+t._s(e.name)+" ")])})),0),r("p"),r("p",[r("span",[t._v("Submitted"+t._s(" ")+t._s(" "))]),r("span",{staticClass:"wemd-black-text"},[t._v(t._s(t.result.problemForm.problem.submitStatistics.allSubmitTimes))])]),r("p",[r("span",[t._v("Solved"+t._s(" ")+t._s(" "))]),r("span",{staticClass:"wemd-black-text"},[t._v(t._s(t.result.problemForm.problem.submitStatistics.acSubmitTimes))])]),r("p",[r("span",[t._v("AC rate"+t._s(" ")+t._s(" "))]),r("span",{staticClass:"wemd-black-text"},[t._v(t._s(0==t.result.problemForm.problem.submitStatistics.allSubmitTimes?"---":(100*t.result.problemForm.problem.submitStatistics.acSubmitTimes/t.result.problemForm.problem.submitStatistics.allSubmitTimes).toFixed(2)+"%"))])]),r("p",[r("span",[t._v("Date"+t._s(" ")+t._s(" "))]),r("span",{staticClass:"wemd-black-text"},[t._v(t._s(t.formatTime(t.result.problemForm.problem.createTime)))])])],1)],1)])]),r("footers")],1)],1)},o=[],i=r("6f5f"),n=i["a"],l=(r("541f"),r("2877")),c=Object(l["a"])(n,s,o,!1,null,"a5a1cd06",null),p=c.exports,m=r("4eb5"),d=r.n(m),u=r("8c4f");a["a"].use(u["a"]);var b=[{path:"/problem",name:"problem",meta:{title:"client 登录"}}],f=new u["a"]({mode:"history",routes:b}),h=r("a925"),_=r("edad"),v=r("2cd5");r("3495");a["a"].config.productionTip=!1,a["a"].prototype.GLOBAL=_["a"],a["a"].use(h["a"]),a["a"].use(d.a);var g=new h["a"]({locale:_["a"].OJ_DEFAULT_LANG,messages:v["a"].messages});new a["a"]({i18n:g,router:f,render:function(t){return t(p)}}).$mount("#problem")}});
//# sourceMappingURL=problem.3f129ce4.js.map