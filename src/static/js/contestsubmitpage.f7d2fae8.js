(function(l){function t(t){for(var e,i,s=t[0],r=t[1],o=t[2],n=0,a=[];n<s.length;n++)i=s[n],Object.prototype.hasOwnProperty.call(d,i)&&d[i]&&a.push(d[i][0]),d[i]=0;for(e in r)Object.prototype.hasOwnProperty.call(r,e)&&(l[e]=r[e]);p&&p(t);while(a.length)a.shift()();return u.push.apply(u,o||[]),c()}function c(){for(var t,e=0;e<u.length;e++){for(var i=u[e],s=!0,r=1;r<i.length;r++){var o=i[r];0!==d[o]&&(s=!1)}s&&(u.splice(e--,1),t=n(n.s=i[0]))}return t}var i={},d={contestsubmitpage:0},u=[];function n(t){if(i[t])return i[t].exports;var e=i[t]={i:t,l:!1,exports:{}};return l[t].call(e.exports,e,e.exports,n),e.l=!0,e.exports}n.m=l,n.c=i,n.d=function(t,e,i){n.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:i})},n.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},n.t=function(e,t){if(1&t&&(e=n(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var i=Object.create(null);if(n.r(i),Object.defineProperty(i,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var s in e)n.d(i,s,function(t){return e[t]}.bind(null,s));return i},n.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return n.d(e,"a",e),e},n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},n.p="/";var e=window["webpackJsonp"]=window["webpackJsonp"]||[],s=e.push.bind(e);e.push=t,e=e.slice();for(var r=0;r<e.length;r++)t(e[r]);var p=s;u.push([13,"chunk-vendors","chunk-common"]),c()})({"0b4f":function(t,e,i){"use strict";i.r(e);i("e260"),i("e6cf"),i("cca6"),i("a79d");var s=i("a026"),r=function(){var s=this,t=s.$createElement,r=s._self._c||t;return r("div",{attrs:{id:"contestsubmitpage"}},[r("loadings",{attrs:{isLoading:s.settings.base.beforeLoad?1:0}}),s.settings.base.beforeLoad?s._e():r("div",{staticStyle:{display:"flex","flex-direction":"column","min-height":"100vh"}},[r("div",{staticClass:"immersive-container",attrs:{id:"editor-container"}},[r("top-side",{class:{"problem-only":s.settings.view.problemOpen&&!s.settings.view.editOpen,"editor-only":!s.settings.view.problemOpen&&s.settings.view.editOpen}},[r("slide-curtain",{staticStyle:{display:"none",height:"658.4px"}}),r("left-side",{staticStyle:{width:"49.82vw"}},[r("div",{class:{"left-side-pre":!0,"container-pro":s.settings.view.problemOpen&&!s.settings.view.editOpen}},[r("div",{staticClass:"prob-header  cm-performance-optimistic "},[r("button",{staticClass:"btn btn-outline-secondary",attrs:{id:"backBtn",onclick:"window.location.href = '/contest/problemset.html?cid="+encodeURIComponent(s.settings.contest.cid)+"'"}},[r("i",{staticClass:"fa fa-hand-o-left",attrs:{"aria-hidden":"true"}}),s._v(" Back ")]),r("div",{staticClass:"dropdown",staticStyle:{"margin-left":"15px"},attrs:{id:"problemSwitcher"}},[r("button",{staticClass:"btn btn-outline-secondary dropdown-toggle",attrs:{type:"button",id:"dropdownMenuButton","data-toggle":"dropdown","aria-haspopup":"true","aria-expanded":"false"}},[s._v(s._s(s.transRankString(s.result.problemForm.problemDetail.rankNum))+" ")]),r("div",{staticClass:"dropdown-menu cm-scrollable-menu",attrs:{"aria-labelledby":"dropdownMenuButton"}},s._l(s.result.contestProblemsetForm.problem,function(t){return r("a",{key:s.key,staticClass:"dropdown-item",attrs:{href:s.result.problemForm.rankNum!=t.rankNum?"/contest/submitpage.html?cid="+encodeURIComponent(s.settings.contest.cid)+"&rid="+encodeURIComponent(t.rankNum):"javascript:void(0)"}},[r("span",{staticStyle:{"margin-right":"10px"}},[0==t.hasPass?[r("i",{staticClass:"fa fa-circle-o",staticStyle:{color:"lightgrey"},attrs:{"aria-hidden":"true"}})]:2==t.hasPass?[r("i",{staticClass:"fa fa-adjust",staticStyle:{color:"orange"},attrs:{"aria-hidden":"true"}})]:[r("i",{staticClass:"fa fa-circle",staticStyle:{color:"lightseagreen"},attrs:{"aria-hidden":"true"}})]],2),s._v(" "+s._s(s.transRankString(t.rankNum)+". "+t.title)+" ")])}),0)]),r("info-badge",{attrs:{"data-toggle":"tooltip","data-placement":"top",title:"","data-original-title":"AC Rate"}},[r("i",{staticClass:"fa fa-star",attrs:{"aria-hidden":"true"}}),s._v(" "+s._s(0==s.result.problemForm.problemDetail.submitStatistics.allSubmitTimes?"---":(100*s.result.problemForm.problemDetail.submitStatistics.acSubmitTimes/s.result.problemForm.problemDetail.submitStatistics.allSubmitTimes).toFixed(2)+"%")+" ")]),r("info-badge",{attrs:{"data-toggle":"tooltip","data-placement":"top",title:"","data-original-title":"Time Limit"}},[r("i",{staticClass:"fa fa-clock-o",attrs:{"aria-hidden":"true"}}),s._v(" "+s._s(s.result.problemForm.problem.timeLimit)+"ms ")]),r("info-badge",{attrs:{"data-toggle":"tooltip","data-placement":"top",title:"","data-original-title":"Memory Limit"}},[r("i",{staticClass:"fa fa-cog",attrs:{"aria-hidden":"true"}}),s._v(" "+s._s((s.result.problemForm.problem.memoryLimit/1024).toFixed(0))+"KB ")]),s.result.problemForm.problem.isSpj?r("info-badge",{staticStyle:{color:"red"}},[s._v(" Special Judge ")]):s._e()],1),r("div",{staticClass:"animated pre-animated cm-performance-optimistic cm-delay "},[r("fresh-container",[r("div",{staticStyle:{"text-align":"center","font-family":"Consolas","white-space":"pre"}},[r("h3",{},[s._v(s._s(s.transRankString(s.result.problemForm.problemDetail.rankNum)+". "+s.result.problemForm.problem.title))])]),r("div",{staticStyle:{"margin-top":"1rem","font-size":"17px"}},[r("i",{staticClass:"fa fa-bars",attrs:{"aria-hidden":"true"}}),r("span",[s._v(" Problem Information")])]),r("div",{staticClass:"problem"},[r("div",{staticClass:"card"},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[s._v("Problem Description")])]),r("div",{staticClass:"card-body"},[r("span",{staticStyle:{"white-space":"pre"},attrs:{id:"description"},domProps:{innerHTML:s._s(s.result.problemForm.problem.problemDescription)}})])]),r("div",{staticClass:"card"},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[s._v("Input Description")])]),r("div",{staticClass:"card-body"},[r("span",{staticStyle:{"white-space":"pre"},attrs:{id:"input-description"},domProps:{innerHTML:s._s(s.result.problemForm.problem.inputDescription)}})])]),r("div",{staticClass:"card",attrs:{"data-v-8b7d93b8":""}},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[s._v("Output Description")])]),r("div",{staticClass:"card-body"},[r("span",{staticStyle:{"white-space":"pre"},attrs:{id:"output-description"},domProps:{innerHTML:s._s(s.result.problemForm.problem.outputDescription)}})])]),s._l(s.result.problemForm.problem.sampleIO,function(e,i){return[r("div",{key:s.key,staticClass:"double-card"},[r("div",{staticClass:"card",staticStyle:{width:"48%",float:"left"}},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[s._v(" Sample Input "+s._s(i+1)+" "),s.settings.problem.copy.id==i&&1==s.settings.problem.copy.ing&&0==s.settings.problem.copy.type?r("a",{attrs:{href:"javascript:void(0)"}},[r("i",{staticClass:"fa fa-check-circle",attrs:{"aria-hidden":"true"}})]):r("a",{attrs:{href:"javascript:void(0)"},on:{click:function(t){return s.handleCopy(e.sample_I,t,i,0)}}},[r("i",{staticClass:"fa fa-files-o",attrs:{"aria-hidden":"true"}})])])]),r("div",{staticClass:"card-body"},[r("span",{staticClass:"sampledata",staticStyle:{"white-space":"pre"},attrs:{id:"sampleinput"+i}},[s._v(s._s(e.sample_I))])])]),r("div",{staticClass:"card",staticStyle:{width:"48%",float:"right"}},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[s._v(" Sample Output "+s._s(i+1)+" "),s.settings.problem.copy.id==i&&1==s.settings.problem.copy.ing&&1==s.settings.problem.copy.type?r("a",{attrs:{href:"javascript:void(0)"}},[r("i",{staticClass:"fa fa-check-circle",attrs:{"aria-hidden":"true"}})]):r("a",{attrs:{href:"javascript:void(0)"},on:{click:function(t){return s.handleCopy(e.sample_O,t,i,1)}}},[r("i",{staticClass:"fa fa-files-o",attrs:{"aria-hidden":"true"}})])])]),r("div",{staticClass:"card-body"},[r("span",{staticClass:"sampledata",staticStyle:{"white-space":"pre"},attrs:{id:"sampleoutput"+i}},[s._v(s._s(e.sample_O))])])]),r("div",{staticStyle:{clear:"both"}})])]}),null!=s.result.problemForm.problem.hint&&""!=s.result.problemForm.problem.hint?r("div",{staticClass:"card"},[r("div",{staticClass:"card-header",staticStyle:{color:"black"}},[r("span",[s._v("Hint")])]),r("div",{staticClass:"card-body"},[r("span",{staticStyle:{"white-space":"pre"},attrs:{id:"hint"},domProps:{innerHTML:s._s(s.result.problemForm.problem.hint)}})])]):s._e()],2)])],1)])]),r("middle-slider"),r("right-side",{staticStyle:{width:"49.82vw","padding-bottom":"10px","padding-top":"10px","padding-right":"5px","background-color":"black"}},[r("editor",{attrs:{theme:"twilight",lang:"c_cpp",height:"100%",options:s.settings.editor},on:{init:s.initEditor},model:{value:s.forms.solutionForm.code,callback:function(t){s.$set(s.forms.solutionForm,"code",t)},expression:"forms.solutionForm.code"}})],1)],1),r("bottom-side",[r("div",[r("button",{class:{btn:!0,"btn-secondary":!0,"cm-active":s.settings.view.problemOpen},attrs:{type:"button",id:"problemBtn"},on:{click:s.toggltProblemButton}},[r("i",{staticClass:"fa fa-book fa-fw",attrs:{"aria-hidden":"true"}})]),r("button",{class:{btn:!0,"btn-secondary":!0,"cm-active":s.settings.view.editOpen},attrs:{type:"button",id:"editorBtn"},on:{click:s.toggltEditButton}},[r("i",{staticClass:"fa fa-pencil-square-o fa-fw",attrs:{"aria-hidden":"true"}})]),r("button",{staticClass:"btn btn-secondary",attrs:{type:"button",id:"historyBtn"}},[r("i",{staticClass:"fa fa-history fa-fw",attrs:{"aria-hidden":"true"}}),s._v(" History ")])]),r("div",[r("div",{staticClass:"btn-group dropup"},[r("button",{staticClass:"btn btn-secondary dropdown-toggle",attrs:{type:"button",id:"cur_lang_selector","data-toggle":"dropdown","aria-haspopup":"true","aria-expanded":"false"}},[s.forms.solutionForm.lang==s.GLOBAL.OJ_LANG_C?[r("i",{staticClass:"devicon-c-plain colored"}),s._v(" C ")]:s.forms.solutionForm.lang==s.GLOBAL.OJ_LANG_CPLUS?[r("i",{staticClass:"devicon-cplusplus-plain colored"}),s._v(" C++ ")]:s.forms.solutionForm.lang==s.GLOBAL.OJ_LANG_CPLUS11?[r("i",{staticClass:"devicon-cplusplus-plain colored"}),s._v(" C++11 ")]:s._e()],2),r("div",{staticClass:"dropdown-menu cm-scrollable-menu"},[r("button",{staticClass:"dropdown-item lang-selector",on:{click:function(t){return s.switchLang(s.GLOBAL.OJ_LANG_C)}}},[r("i",{staticClass:"devicon-c-plain colored"}),s._v(" C ")]),r("button",{staticClass:"dropdown-item lang-selector",on:{click:function(t){return s.switchLang(s.GLOBAL.OJ_LANG_CPLUS)}}},[r("i",{staticClass:"devicon-cplusplus-plain colored"}),s._v(" C++ ")]),r("button",{staticClass:"dropdown-item lang-selector",on:{click:function(t){return s.switchLang(s.GLOBAL.OJ_LANG_CPLUS11)}}},[r("i",{staticClass:"devicon-cplusplus-plain colored"}),s._v(" C++11 ")])]),r("button",{staticClass:"btn btn-primary",attrs:{type:"button",id:"submitBtn"},on:{click:s.postSolutionForm}},[r("i",{staticClass:"fa fa-paper-plane fa-fw",attrs:{"aria-hidden":"true"}}),s._v(" Submit Code ")]),r("button",{staticClass:"btn btn-primary",attrs:{type:"button",id:"resultBtn"}},[-1==s.result.solutionForm.result?r("i",{staticClass:"fa fa-dot-circle-o fa-fw",staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_WAITING?r("i",{staticClass:"fa fa-spin fa-cog fa-fw ",staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_COMPILING||s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_RUNNING_JUDGING||s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_JUDGING?r("i",{class:"fa fa-spin fa-cog fa-fw "+s.GLOBAL.OJ_MESSAGE_RESULT_COLOR_CLASS.get(s.result.solutionForm.result),staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_ACCEPTED?r("i",{class:"fa fa-check fa-fw "+s.GLOBAL.OJ_MESSAGE_RESULT_COLOR_CLASS.get(s.result.solutionForm.result),staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_COMPILATION_ERROR?r("i",{class:"fa fa-exclamation-triangle fa-fw "+s.GLOBAL.OJ_MESSAGE_RESULT_COLOR_CLASS.get(s.result.solutionForm.result),staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_SYSTEM_ERROR||s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_WRONG_ANSWER||s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_RUNTIME_ERROR||s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_PRESENTATION_ERROR||s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_TIME_LIMIT_EXCEEDED||s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_MEMORY_LIMIT_EXCEEDED||s.result.solutionForm.result==s.GLOBAL.OJ_RESULT_OUTPUT_LIMIT_EXCEEDED?r("i",{class:"fa fa-times fa-fw "+s.GLOBAL.OJ_MESSAGE_RESULT_COLOR_CLASS.get(s.result.solutionForm.result),staticStyle:{"font-size":"20px"},attrs:{"aria-hidden":"true"}}):s._e()])])])])],1)])],1)},o=[],n=i("543c"),a=n["a"],l=(i("5a76"),i("2877")),c=Object(l["a"])(a,r,o,!1,null,"a9570226",null),d=c.exports,u=i("8c4f");s["a"].use(u["a"]);var p=[{path:"/contest/submitpage",name:"contests",meta:{title:"client 登录"}}],m=new u["a"]({mode:"history",routes:p}),h=i("a925"),f=i("edad"),b=i("2cd5");i("3495");s["a"].config.productionTip=!1,s["a"].prototype.GLOBAL=f["a"],s["a"].use(h["a"]);var O=new h["a"]({locale:f["a"].OJ_DEFAULT_LANG,messages:b["a"].messages});new s["a"]({i18n:O,router:m,render:function(t){return t(d)}}).$mount("#contestsubmitpage")},13:function(t,e,i){t.exports=i("0b4f")},"543c":function(t,i,s){"use strict";(function(a){s("96cf");var l=s("1da1"),t=s("54a1"),o=s("2cd5"),e=s("6e08"),c=s.n(e);i["a"]={name:"contestsubmitpage",components:{editor:s("7c9e"),Loadings:t["a"]},data:function(){return{settings:{base:{belong:"submit",isLogin:!1,beforeLoad:!0,user:new Object},contest:{cid:0},problem:{rid:0,copy:{id:"",ing:!1,type:""}},editor:{enableBasicAutocompletion:!0,enableSnippets:!0,enableLiveAutocompletion:!0,tabSize:4,fontSize:18,showPrintMargin:!1},view:{problemOpen:!0,editOpen:!0}},forms:{problemForm:{rid:0,cid:0},contestProblemsetForm:{cid:0},solutionForm:{rid:0,cid:0,code:"",lang:0},refreshForm:{cid:0,sid:0}},result:{solutionForm:{sid:0,result:-1},problemForm:{problem:new Object,problemDetail:new Object},contestProblemsetForm:{problem:[]}}}},methods:{transRankString:function(t){return this.GLOBAL.OJ_MESSAGE_RANK_STRING[t]},initEditor:function(){s("2099"),s("2b41"),s("79fb")},handleCopy:function(t,e,i,s){o["a"].handleClipboard(t,e),this.settings.problem.copy.id=i,this.settings.problem.copy.type=s,this.settings.problem.copy.ing=!0;var r=this;setTimeout(function(){r.settings.problem.copy.ing=!1},500)},toggltProblemButton:function(){this.settings.view.problemOpen?this.settings.view.editOpen&&(this.settings.view.problemOpen=!1):this.settings.view.problemOpen=!0},toggltEditButton:function(){this.settings.view.editOpen?this.settings.view.problemOpen&&(this.settings.view.editOpen=!1):this.settings.view.editOpen=!0},switchLang:function(t){this.forms.solutionForm.lang=t},postSolutionForm:function(){this.result.solutionForm.result=this.GLOBAL.OJ_RESULT_WAITING,this.result.solutionForm.sid=0;var s=this;window.axios.post(this.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/contest/solution/submit",o["a"].stringify(s.forms.solutionForm),{headers:{OJ_ACCESS_TOKEN:o["a"].getCookie("AccessToken")}}).then(function(t){var e=t.data,i=e.OJ_ERROR_TYPE;i==s.GLOBAL.OJ_ERROR_NO_ERROR?(s.result.solutionForm.sid=e.OJ_SOLUTION_ID,s.refreshResult()):i==s.GLOBAL.OJ_ERROR_NOT_LOGIN&&(window.location.href="/loginpage.html")}).catch(function(t){console.log(t)})},refreshResult:function(){if(0!=this.result.solutionForm.sid&&(-1==this.result.solutionForm.result||this.result.solutionForm.result==this.GLOBAL.OJ_RESULT_WAITING||this.result.solutionForm.result==this.GLOBAL.OJ_RESULT_COMPILING||this.result.solutionForm.result==this.GLOBAL.OJ_RESULT_RUNNING_JUDGING||this.result.solutionForm.result==this.GLOBAL.OJ_RESULT_JUDGING)){var s=this;window.axios.post(this.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/contest/solution/get",o["a"].stringify(s.result.solutionForm),{headers:{OJ_ACCESS_TOKEN:o["a"].getCookie("AccessToken")}}).then(function(t){var e=t.data,i=e.OJ_ERROR_TYPE;i==s.GLOBAL.OJ_ERROR_NO_ERROR?(s.result.solutionForm.result=e.OJ_RESULT,setTimeout(function(){s.refreshResult()},2e3)):i==s.GLOBAL.OJ_ERROR_NOT_LOGIN&&(window.location.href="/loginpage.html")}).catch(function(t){console.log(t)})}},refresh:function(){var e=this;return Object(l["a"])(regeneratorRuntime.mark(function t(){var i;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:i=e,setTimeout(Object(l["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,o["a"].refresh();case 2:e=t.sent,e.OJ_ERROR_TYPE==i.GLOBAL.OJ_ERROR_NO_ERROR?(i.settings.base.isLogin=!0,i.settings.base.userInfo=e.OJ_USER_INFO):window.location.href="/loginpage.html",i.refresh();case 5:case"end":return t.stop()}},t)})),12e5);case 2:case"end":return t.stop()}},t)}))()},identify:function(){var t=Object(l["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,o["a"].idenfity();case 2:return e=t.sent,this.$i18n.locale=e.OJ_LANGUAGE_TYPE,e.OJ_ERROR_TYPE==this.GLOBAL.OJ_ERROR_NO_ERROR&&(this.settings.base.isLogin=!0,this.settings.base.userInfo=e.OJ_USER_INFO),this.refresh(),t.abrupt("return",e);case 7:case"end":return t.stop()}},t,this)}));function e(){return t.apply(this,arguments)}return e}(),searchContestProblemInfo:function(){var t=this;return window.axios.post(t.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/contest/problem/get",o["a"].stringify(t.forms.problemForm),{headers:{OJ_ACCESS_TOKEN:o["a"].getCookie("AccessToken")}})},searchContestProblemList:function(){var t=this;return window.axios.post(t.GLOBAL.OJ_DEFAULT_BACK_SERVE+"/contest/problem/list",o["a"].stringify(t.forms.contestProblemsetForm),{headers:{OJ_ACCESS_TOKEN:o["a"].getCookie("AccessToken")}})},vditorInit:function(t){c.a.setContentTheme("light","https://cdn.jsdelivr.net/npm/vditor@3.8.4/dist/css/content-theme"),c.a.codeRender(t,"zh_CN"),c.a.highlightRender({enable:!0,lineNumber:!1,style:"github"},t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4"),c.a.mathRender(t,{cdn:"https://cdn.jsdelivr.net/npm/vditor@3.8.4",math:{engine:"KaTeX",inlineDigit:!1,macros:{}}}),c.a.mermaidRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4","classic"),c.a.flowchartRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4"),c.a.graphvizRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4"),c.a.chartRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4","classic"),c.a.mindmapRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4","classic"),c.a.abcRender(t,"https://cdn.jsdelivr.net/npm/vditor@3.8.4"),c.a.mediaRender(t)}},created:function(){var e=this;return Object(l["a"])(regeneratorRuntime.mark(function t(){var n;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,e.identify();case 2:e.forms.refreshForm.cid=e.forms.contestProblemsetForm.cid=e.forms.solutionForm.cid=e.forms.problemForm.cid=e.settings.contest.cid=o["a"].getUrlKey("cid"),e.forms.solutionForm.rid=e.forms.problemForm.rid=e.settings.problem.rid=o["a"].getUrlKey("rid"),null==e.settings.contest.cid&&(window.location.href="/contests.html"),null==e.settings.problem.rid&&(window.location.href="/contest/problemset.html?cid="+encodeURIComponent(e.settings.contest.cid)),n=e,window.axios.all([n.searchContestProblemInfo(),n.searchContestProblemList()]).then(window.axios.spread(function(t,e){var i=t.data,s=e.data,r=i.OJ_ERROR_TYPE,o=s.OJ_ERROR_TYPE;r==n.GLOBAL.OJ_ERROR_NO_ERROR&&o==n.GLOBAL.OJ_ERROR_NO_ERROR?n.settings.base.beforeLoad=!1:r==n.GLOBAL.OJ_ERROR_NON_EXISTENT_PROBLEM?window.location.href="/contests.html":r==n.GLOBAL.OJ_ERROR_NOT_LOGIN&&(window.location.href="/loginpage.html"),console.log(s),console.log(i),n.result.problemForm.problem=i.OJ_PROBLEM,n.result.problemForm.problemDetail=i.OJ_RANKSTATE,n.result.contestProblemsetForm.problem=s.OJ_PROBLEM,c.a.md2html(n.result.problemForm.problem.problemDescription).then(function(t){n.result.problemForm.problem.problemDescription=t,setTimeout(Object(l["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return e=document.getElementById("description"),t.next=3,n.vditorInit(e);case 3:case"end":return t.stop()}},t)})),2)}),c.a.md2html(n.result.problemForm.problem.inputDescription).then(function(t){n.result.problemForm.problem.inputDescription=t,setTimeout(Object(l["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return e=document.getElementById("input-description"),t.next=3,n.vditorInit(e);case 3:case"end":return t.stop()}},t)})),2)}),c.a.md2html(n.result.problemForm.problem.outputDescription).then(function(t){n.result.problemForm.problem.outputDescription=t,setTimeout(Object(l["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return e=document.getElementById("output-description"),t.next=3,n.vditorInit(e);case 3:case"end":return t.stop()}},t)})),2)}),c.a.md2html(n.result.problemForm.problem.hint).then(function(t){n.result.problemForm.problem.hint=t,setTimeout(Object(l["a"])(regeneratorRuntime.mark(function t(){var e;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return e=document.getElementById("hint"),t.next=3,n.vditorInit(e);case 3:case"end":return t.stop()}},t)})),2)}),document.title=n.result.problemForm.problem.title+" | CTGUACM",a(document).ready(function(){a(".double-card").each(function(){var t,e=a(this).children(".card:first").children(".card-body").height(),i=a(this).children(".card:last").children(".card-body").height();t=e>i?e:i,a(this).children(".card:first").children(".card-body").css("height",t+25+"px"),a(this).children(".card:last").children(".card-body").css("height",t+25+"px")});var s="49%";a("middle-slider").mousedown(function(){a("slide-curtain").fadeIn(0),a("slide-curtain").mousemove(function(t){s=t.pageX<=.9*window.innerWidth&&t.pageX>=.4*window.innerWidth?t.pageX-.0035*window.innerWidth:t.pageX>.9*window.innerWidth?.9*window.innerWidth-.0035*window.innerWidth:.4*window.innerWidth-.0035*window.innerWidth;var e=s/window.innerWidth*100,i=(.9965*window.innerWidth-s)/window.innerWidth*100;a("left-side").css("width",e+"vw"),a("right-side").css("width",i+"vw")})}),a("slide-curtain").mouseup(function(){a("slide-curtain").fadeOut(0),console.log(s)})})})).catch(function(t){console.log(t)});case 8:case"end":return t.stop()}},t)}))()}}}).call(this,s("1157"))},"5a76":function(t,e,i){"use strict";var s=i("b647"),r=i.n(s);r.a},b647:function(t,e,i){}});