(function(l){function t(t){for(var e,i,r=t[0],s=t[1],n=t[2],o=0,a=[];o<r.length;o++)i=r[o],Object.prototype.hasOwnProperty.call(d,i)&&d[i]&&a.push(d[i][0]),d[i]=0;for(e in s)Object.prototype.hasOwnProperty.call(s,e)&&(l[e]=s[e]);p&&p(t);while(a.length)a.shift()();return u.push.apply(u,n||[]),c()}function c(){for(var t,e=0;e<u.length;e++){for(var i=u[e],r=!0,s=1;s<i.length;s++){var n=i[s];0!==d[n]&&(r=!1)}r&&(u.splice(e--,1),t=o(o.s=i[0]))}return t}var i={},d={submitpage:0},u=[];function o(t){if(i[t])return i[t].exports;var e=i[t]={i:t,l:!1,exports:{}};return l[t].call(e.exports,e,e.exports,o),e.l=!0,e.exports}o.m=l,o.c=i,o.d=function(t,e,i){o.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:i})},o.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},o.t=function(e,t){if(1&t&&(e=o(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var i=Object.create(null);if(o.r(i),Object.defineProperty(i,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)o.d(i,r,function(t){return e[t]}.bind(null,r));return i},o.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return o.d(e,"a",e),e},o.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},o.p="/";var e=window["webpackJsonp"]=window["webpackJsonp"]||[],r=e.push.bind(e);e.push=t,e=e.slice();for(var s=0;s<e.length;s++)t(e[s]);var p=r;u.push([7,"chunk-vendors","chunk-common"]),c()})({"50c0":function(t,i,r){"use strict";(function(s){r("96cf");var n=r("1da1"),t=r("54a1"),o=r("2cd5"),e=r("6e08"),a=r.n(e);i["a"]={data:function(){return{settings:{base:{belong:"submit",isLogin:!1,beforeLoad:!0,user:new Object},problem:{pid:0,copy:{id:"",ing:!1,type:""}},editor:{enableBasicAutocompletion:!0,enableSnippets:!0,enableLiveAutocompletion:!0,tabSize:4,fontSize:18,showPrintMargin:!1},view:{problemOpen:!0,editOpen:!0}},forms:{problemForm:{pid:0},solutionForm:{pid:0,code:"",lang:0},refreshForm:{sid:0}},result:{solutionForm:{sid:0,result:-1},problemForm:{problem:new Object}}}},methods:{initEditor:function(){r("2099"),r("2b41"),r("79fb")},handleCopy:function(t,e,i,r){o["a"].handleClipboard(t,e),this.settings.problem.copy.id=i,this.settings.problem.copy.type=r,this.settings.problem.copy.ing=!0;var s=this;setTimeout(function(){s.settings.problem.copy.ing=!1},500)},toggltProblemButton:function(){this.settings.view.problemOpen?this.settings.view.editOpen&&(this.settings.view.problemOpen=!1):this.settings.view.problemOpen=!0},toggltEditButton:function(){this.settings.view.editOpen?this.settings.view.problemOpen&&(this.settings.view.editOpen=!1):this.settings.view.editOpen=!0},switchLang:function(t){this.forms.solutionForm.lang=t},postSolutionForm:function(){this.result.solutionForm.result=this.GLOBAL.OJ_RESULT_WAITING,this.result.solutionForm.sid=0;var r=this;window.axios.post(this.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/solution/submit",o["a"].stringify(r.forms.solutionForm),{headers:{OJ_ACCESS_TOKEN:o["a"].getCookie("AccessToken")}}).then(function(t){var e=t.data,i=e.OJ_ERROR_TYPE;i==r.GLOBAL.OJ_ERROR_NO_ERROR?(r.result.solutionForm.sid=r.forms.refreshForm.sid=e.OJ_SOLUTION_ID,r.refreshResult()):i==r.GLOBAL.OJ_ERROR_NOT_LOGIN&&(window.location.href="/loginpage.html")}).catch(function(t){console.log(t)})},refreshResult:function(){if(0!=this.result.solutionForm.sid&&(-1==this.result.solutionForm.result||this.result.solutionForm.result==this.GLOBAL.OJ_RESULT_WAITING||this.result.solutionForm.result==this.GLOBAL.OJ_RESULT_COMPILING||this.result.solutionForm.result==this.GLOBAL.OJ_RESULT_RUNNING_JUDGING||this.result.solutionForm.result==this.GLOBAL.OJ_RESULT_JUDGING)){var r=this;window.axios.post(this.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/solution/get",o["a"].stringify(r.forms.refreshForm),{headers:{OJ_ACCESS_TOKEN:o["a"].getCookie("AccessToken")}}).then(function(t){var e=t.data,i=e.OJ_ERROR_TYPE;i==r.GLOBAL.OJ_ERROR_NO_ERROR?(r.result.solutionForm.result=e.OJ_RESULT,setTimeout(function(){r.refreshResult()},1e3)):i==r.GLOBAL.OJ_ERROR_NOT_LOGIN&&(window.location.href="/loginpage.html")}).catch(function(t){console.log(t)})}},refresh:function(){var e=this;return Object(n["a"])(regeneratorRuntime.mark(function t(){var i;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:i=e,setTimeout(Object(n["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,o["a"].refresh();case 2:e=t.sent,e.OJ_ERROR_TYPE==i.GLOBAL.OJ_ERROR_NO_ERROR?(i.settings.base.isLogin=!0,i.settings.base.userInfo=e.OJ_USER_INFO):window.location.href="/loginpage.html",i.refresh();case 5:case"end":return t.stop()}},t)})),12e5);case 2:case"end":return t.stop()}},t)}))()},identify:function(){var t=Object(n["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,o["a"].idenfity();case 2:return e=t.sent,this.$i18n.locale=e.OJ_LANGUAGE_TYPE,e.OJ_ERROR_TYPE==this.GLOBAL.OJ_ERROR_NO_ERROR?(this.settings.base.isLogin=!0,this.settings.base.userInfo=e.OJ_USER_INFO):window.location.href="/problemset.html",this.refresh(),t.abrupt("return",e);case 7:case"end":return t.stop()}},t,this)}));function e(){return t.apply(this,arguments)}return e}(),searchProblem:function(){var t=this;return window.axios.post(t.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/problem/get",o["a"].stringify(t.forms.problemForm),{headers:{OJ_ACCESS_TOKEN:o["a"].getCookie("AccessToken")}})},vditorInit:function(t){a.a.setContentTheme("light","https://cdn.jsdelivr.net/npm/vditor@3.8.4/dist/css/content-theme"),a.a.codeRender(t,"zh_CN"),a.a.highlightRender({enable:!0,lineNumber:!1,style:"github"},t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4"),a.a.mathRender(t,{cdn:"https://cdn.jsdelivr.net/npm/vditor@3.8.4",math:{engine:"KaTeX",inlineDigit:!1,macros:{}}}),a.a.mermaidRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4","classic"),a.a.flowchartRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4"),a.a.graphvizRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4"),a.a.chartRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4","classic"),a.a.mindmapRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4","classic"),a.a.abcRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4"),a.a.mediaRender(t)}},components:{editor:r("7c9e"),Loadings:t["a"]},name:"submitpage",created:function(){var e=this;return Object(n["a"])(regeneratorRuntime.mark(function t(){var r;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,e.identify();case 2:e.forms.solutionForm.pid=e.forms.problemForm.pid=e.settings.problem.pid=o["a"].getUrlKey("pid"),null==e.forms.problemForm.pid&&(window.location.href="/problemset.html"),r=e,window.axios.all([r.searchProblem()]).then(window.axios.spread(function(t){var e=t.data;r.result.problemForm.problem=e.OJ_PROBLEM;var i=e.OJ_ERROR_TYPE;i==r.GLOBAL.OJ_ERROR_NO_ERROR?r.settings.base.beforeLoad=!1:i==r.GLOBAL.OJ_ERROR_NON_EXISTENT_PROBLEM?window.location.href="/problemset.html":i==r.GLOBAL.OJ_ERROR_NOT_LOGIN&&(window.location.href="/loginpage.html"),a.a.md2html(r.result.problemForm.problem.problemDescription).then(function(t){r.result.problemForm.problem.problemDescription=t,setTimeout(Object(n["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return e=document.getElementById("description"),t.next=3,r.vditorInit(e);case 3:case"end":return t.stop()}},t)})),2)}),a.a.md2html(r.result.problemForm.problem.inputDescription).then(function(t){r.result.problemForm.problem.inputDescription=t,setTimeout(Object(n["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return e=document.getElementById("input-description"),t.next=3,r.vditorInit(e);case 3:case"end":return t.stop()}},t)})),2)}),a.a.md2html(r.result.problemForm.problem.outputDescription).then(function(t){r.result.problemForm.problem.outputDescription=t,setTimeout(Object(n["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return e=document.getElementById("output-description"),t.next=3,r.vditorInit(e);case 3:case"end":return t.stop()}},t)})),2)}),a.a.md2html(r.result.problemForm.problem.hint).then(function(t){r.result.problemForm.problem.hint=t,setTimeout(Object(n["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return e=document.getElementById("hint"),t.next=3,r.vditorInit(e);case 3:case"end":return t.stop()}},t)})),2)}),document.title=r.result.problemForm.problem.title+" | CTGUACM",s(document).ready(function(){s(".double-card").each(function(){var t,e=s(this).children(".card:first").children(".card-body").height(),i=s(this).children(".card:last").children(".card-body").height();t=e>i?e:i,s(this).children(".card:first").children(".card-body").css("height",t+25+"px"),s(this).children(".card:last").children(".card-body").css("height",t+25+"px")});var r="49%";s("middle-slider").mousedown(function(){s("slide-curtain").fadeIn(0),s("slide-curtain").mousemove(function(t){r=t.pageX<=.9*window.innerWidth&&t.pageX>=.4*window.innerWidth?t.pageX-.0035*window.innerWidth:t.pageX>.9*window.innerWidth?.9*window.innerWidth-.0035*window.innerWidth:.4*window.innerWidth-.0035*window.innerWidth;var e=r/window.innerWidth*100,i=(.9965*window.innerWidth-r)/window.innerWidth*100;s("left-side").css("width",e+"vw"),s("right-side").css("width",i+"vw")})}),s("slide-curtain").mouseup(function(){s("slide-curtain").fadeOut(0),console.log(r)})})})).catch(function(t){console.log(t)});case 6:case"end":return t.stop()}},t)}))()}}}).call(this,r("1157"))},"56f5":function(t,e,i){"use strict";i.r(e);i("e260"),i("e6cf"),i("cca6"),i("a79d");var r=i("a026"),s=function(){var r=this,t=r.$createElement,s=r._self._c||t;return s("div",{attrs:{id:"submitpage"}},[s("loadings",{attrs:{isLoading:r.settings.base.beforeLoad?1:0}}),r.settings.base.beforeLoad?r._e():s("div",{staticStyle:{display:"flex","flex-direction":"column","min-height":"100vh"}},[s("div",{staticClass:"immersive-container",attrs:{id:"editor-container"}},[s("top-side",{class:{"problem-only":r.settings.view.problemOpen&&!r.settings.view.editOpen,"editor-only":!r.settings.view.problemOpen&&r.settings.view.editOpen}},[s("slide-curtain",{staticStyle:{display:"none",height:"658.4px"}}),s("left-side",{staticStyle:{width:"49.82vw"}},[s("div",{class:{"left-side-pre":!0,"container-pro":r.settings.view.problemOpen&&!r.settings.view.editOpen}},[s("div",{staticClass:"prob-header animated pre-animated cm-performance-optimistic fadeInLeft"},[s("button",{staticClass:"btn btn-outline-secondary",attrs:{id:"backBtn",onclick:"window.location.href = '/problemset.html?pid="+encodeURIComponent(r.settings.problem.pid)+"'"}},[s("i",{staticClass:"fa fa-hand-o-left",attrs:{"aria-hidden":"true"}}),r._v(" Back ")]),s("info-badge",{attrs:{"data-toggle":"tooltip","data-placement":"top",title:"","data-original-title":"AC Rate"}},[s("i",{staticClass:"fa fa-star",attrs:{"aria-hidden":"true"}}),r._v(" "+r._s(0==r.result.problemForm.problem.submitStatistics.allSubmitTimes?"---":(100*r.result.problemForm.problem.submitStatistics.acSubmitTimes/r.result.problemForm.problem.submitStatistics.allSubmitTimes).toFixed(2)+"%")+" ")]),s("info-badge",{attrs:{"data-toggle":"tooltip","data-placement":"top",title:"","data-original-title":"Time Limit"}},[s("i",{staticClass:"fa fa-clock-o",attrs:{"aria-hidden":"true"}}),r._v(" "+r._s(r.result.problemForm.problem.timeLimit)+"ms ")]),s("info-badge",{attrs:{"data-toggle":"tooltip","data-placement":"top",title:"","data-original-title":"Memory Limit"}},[s("i",{staticClass:"fa fa-cog",attrs:{"aria-hidden":"true"}}),r._v(" "+r._s((r.result.problemForm.problem.memoryLimit/1024).toFixed(0))+"KB ")]),r.result.problemForm.problem.isSpj?s("info-badge",{staticStyle:{color:"red"}},[r._v(" Special Judge ")]):r._e()],1),s("div",{staticClass:"animated pre-animated cm-performance-optimistic cm-delay fadeInLeft"},[s("fresh-container",[s("div",{staticStyle:{"text-align":"center","font-family":"Consolas","white-space":"pre"}},[s("h3",{},[r._v(r._s(r.result.problemForm.problem.problemId+". "+r.result.problemForm.problem.title))])]),s("div",{staticStyle:{"margin-top":"1rem","font-size":"17px"}},[s("i",{staticClass:"fa fa-bars",attrs:{"aria-hidden":"true"}}),s("span",[r._v(" Problem Information")])]),s("div",{staticClass:"problem"},[s("div",{staticClass:"card"},[s("div",{staticClass:"card-header",staticStyle:{color:"black"}},[s("span",[r._v("Problem Description")])]),s("div",{staticClass:"card-body"},[s("span",{staticStyle:{"white-space":"pre"},attrs:{id:"description"},domProps:{innerHTML:r._s(r.result.problemForm.problem.problemDescription)}})])]),s("div",{staticClass:"card"},[s("div",{staticClass:"card-header",staticStyle:{color:"black"}},[s("span",[r._v("Input Description")])]),s("div",{staticClass:"card-body"},[s("span",{staticStyle:{"white-space":"pre"},attrs:{id:"input-description"},domProps:{innerHTML:r._s(r.result.problemForm.problem.inputDescription)}})])]),s("div",{staticClass:"card",attrs:{"data-v-8b7d93b8":""}},[s("div",{staticClass:"card-header",staticStyle:{color:"black"}},[s("span",[r._v("Output Description")])]),s("div",{staticClass:"card-body"},[s("span",{staticStyle:{"white-space":"pre"},attrs:{id:"output-description"},domProps:{innerHTML:r._s(r.result.problemForm.problem.outputDescription)}})])]),r._l(r.result.problemForm.problem.sampleIO,function(e,i){return[s("div",{key:r.key,staticClass:"double-card"},[s("div",{staticClass:"card",staticStyle:{width:"48%",float:"left"}},[s("div",{staticClass:"card-header",staticStyle:{color:"black"}},[s("span",[r._v(" Sample Input "+r._s(i+1)+" "),r.settings.problem.copy.id==i&&1==r.settings.problem.copy.ing&&0==r.settings.problem.copy.type?s("a",{attrs:{href:"javascript:void(0)"}},[s("i",{staticClass:"fa fa-check-circle",attrs:{"aria-hidden":"true"}})]):s("a",{attrs:{href:"javascript:void(0)"},on:{click:function(t){return r.handleCopy(e.sample_I,t,i,0)}}},[s("i",{staticClass:"fa fa-files-o",attrs:{"aria-hidden":"true"}})])])]),s("div",{staticClass:"card-body"},[s("span",{staticClass:"sampledata",staticStyle:{"white-space":"pre"},attrs:{id:"sampleinput"+i}},[r._v(r._s(e.sample_I))])])]),s("div",{staticClass:"card",staticStyle:{width:"48%",float:"right"}},[s("div",{staticClass:"card-header",staticStyle:{color:"black"}},[s("span",[r._v(" Sample Output "+r._s(i+1)+" "),r.settings.problem.copy.id==i&&1==r.settings.problem.copy.ing&&1==r.settings.problem.copy.type?s("a",{attrs:{href:"javascript:void(0)"}},[s("i",{staticClass:"fa fa-check-circle",attrs:{"aria-hidden":"true"}})]):s("a",{attrs:{href:"javascript:void(0)"},on:{click:function(t){return r.handleCopy(e.sample_O,t,i,1)}}},[s("i",{staticClass:"fa fa-files-o",attrs:{"aria-hidden":"true"}})])])]),s("div",{staticClass:"card-body"},[s("span",{staticClass:"sampledata",staticStyle:{"white-space":"pre"},attrs:{id:"sampleoutput"+i}},[r._v(r._s(e.sample_O))])])]),s("div",{staticStyle:{clear:"both"}})])]}),null!=r.result.problemForm.problem.hint&&""!=r.result.problemForm.problem.hint?s("div",{staticClass:"card"},[s("div",{staticClass:"card-header",staticStyle:{color:"black"}},[s("span",[r._v("Hint")])]),s("div",{staticClass:"card-body"},[s("span",{staticStyle:{"white-space":"pre"},attrs:{id:"hint"},domProps:{innerHTML:r._s(r.result.problemForm.problem.hint)}})])]):r._e()],2)])],1)])]),s("middle-slider"),s("right-side",{staticStyle:{width:"49.82vw","padding-bottom":"10px","padding-top":"10px","padding-right":"5px","background-color":"black"}},[s("editor",{attrs:{theme:"twilight",lang:"c_cpp",height:"100%",options:r.settings.editor},on:{init:r.initEditor},model:{value:r.forms.solutionForm.code,callback:function(t){r.$set(r.forms.solutionForm,"code",t)},expression:"forms.solutionForm.code"}})],1)],1),s("bottom-side",[s("div",[s("button",{class:{btn:!0,"btn-secondary":!0,"cm-active":r.settings.view.problemOpen},attrs:{type:"button",id:"problemBtn"},on:{click:r.toggltProblemButton}},[s("i",{staticClass:"fa fa-book fa-fw",attrs:{"aria-hidden":"true"}})]),s("button",{class:{btn:!0,"btn-secondary":!0,"cm-active":r.settings.view.editOpen},attrs:{type:"button",id:"editorBtn"},on:{click:r.toggltEditButton}},[s("i",{staticClass:"fa fa-pencil-square-o fa-fw",attrs:{"aria-hidden":"true"}})]),s("button",{staticClass:"btn btn-secondary",attrs:{type:"button",id:"historyBtn"}},[s("i",{staticClass:"fa fa-history fa-fw",attrs:{"aria-hidden":"true"}}),r._v(" History ")])]),s("div",[s("div",{staticClass:"btn-group dropup"},[s("button",{staticClass:"btn btn-secondary dropdown-toggle",attrs:{type:"button",id:"cur_lang_selector","data-toggle":"dropdown","aria-haspopup":"true","aria-expanded":"false"}},[r.forms.solutionForm.lang==r.GLOBAL.OJ_LANG_C?[s("i",{staticClass:"devicon-c-plain colored"}),r._v(" C ")]:r.forms.solutionForm.lang==r.GLOBAL.OJ_LANG_CPLUS?[s("i",{staticClass:"devicon-cplusplus-plain colored"}),r._v(" C++ ")]:r.forms.solutionForm.lang==r.GLOBAL.OJ_LANG_CPLUS11?[s("i",{staticClass:"devicon-cplusplus-plain colored"}),r._v(" C++11 ")]:r._e()],2),s("div",{staticClass:"dropdown-menu cm-scrollable-menu"},[s("button",{staticClass:"dropdown-item lang-selector",on:{click:function(t){return r.switchLang(r.GLOBAL.OJ_LANG_C)}}},[s("i",{staticClass:"devicon-c-plain colored"}),r._v(" C ")]),s("button",{staticClass:"dropdown-item lang-selector",on:{click:function(t){return r.switchLang(r.GLOBAL.OJ_LANG_CPLUS)}}},[s("i",{staticClass:"devicon-cplusplus-plain colored"}),r._v(" C++ ")]),s("button",{staticClass:"dropdown-item lang-selector",on:{click:function(t){return r.switchLang(r.GLOBAL.OJ_LANG_CPLUS11)}}},[s("i",{staticClass:"devicon-cplusplus-plain colored"}),r._v(" C++11 ")])]),s("button",{staticClass:"btn btn-primary",attrs:{type:"button",id:"submitBtn"},on:{click:r.postSolutionForm}},[s("i",{staticClass:"fa fa-paper-plane fa-fw",attrs:{"aria-hidden":"true"}}),r._v(" Submit Code ")]),s("button",{staticClass:"btn btn-primary",attrs:{type:"button",id:"resultBtn"}},[-1==r.result.solutionForm.result?s("i",{staticClass:"fa fa-dot-circle-o fa-fw",staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_WAITING?s("i",{staticClass:"fa fa-spin fa-cog fa-fw ",staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_COMPILING||r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_RUNNING_JUDGING||r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_JUDGING?s("i",{class:"fa fa-spin fa-cog fa-fw "+r.GLOBAL.OJ_MESSAGE_RESULT_COLOR_CLASS.get(r.result.solutionForm.result),staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_ACCEPTED?s("i",{class:"fa fa-check fa-fw "+r.GLOBAL.OJ_MESSAGE_RESULT_COLOR_CLASS.get(r.result.solutionForm.result),staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_COMPILATION_ERROR?s("i",{class:"fa fa-exclamation-triangle fa-fw "+r.GLOBAL.OJ_MESSAGE_RESULT_COLOR_CLASS.get(r.result.solutionForm.result),staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_SYSTEM_ERROR||r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_WRONG_ANSWER||r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_RUNTIME_ERROR||r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_PRESENTATION_ERROR||r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_TIME_LIMIT_EXCEEDED||r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_MEMORY_LIMIT_EXCEEDED||r.result.solutionForm.result==r.GLOBAL.OJ_RESULT_OUTPUT_LIMIT_EXCEEDED?s("i",{class:"fa fa-times fa-fw "+r.GLOBAL.OJ_MESSAGE_RESULT_COLOR_CLASS.get(r.result.solutionForm.result),staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):r._e()])])])])],1)])],1)},n=[],o=i("50c0"),a=o["a"],l=(i("fe16"),i("2877")),c=Object(l["a"])(a,s,n,!1,null,"0bc820b6",null),d=c.exports,u=i("8c4f");r["a"].use(u["a"]);var p=[{path:"/submitpage",name:"submitpage",meta:{title:"client 登录"}}],m=new u["a"]({mode:"history",routes:p}),h=i("a925"),f=i("edad"),b=i("2cd5");i("3495");r["a"].config.productionTip=!1,r["a"].prototype.GLOBAL=f["a"],r["a"].use(h["a"]);var O=new h["a"]({locale:f["a"].OJ_DEFAULT_LANG,messages:b["a"].messages});new r["a"]({i18n:O,router:m,render:function(t){return t(d)}}).$mount("#submitpage")},7:function(t,e,i){t.exports=i("56f5")},a596:function(t,e,i){},fe16:function(t,e,i){"use strict";var r=i("a596"),s=i.n(r);s.a}});