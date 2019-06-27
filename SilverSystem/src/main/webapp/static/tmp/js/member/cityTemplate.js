
// 城市选择框显示
var inCity = new Object();
inCity.cssParents = function(){ //城市显示
    inCity.top = placeThis.offset().top+7;  //城市选择框  top位置
    inCity.left = placeThis.offset().left;   //城市选择框  left位置
    $(inCity.id).css({"top":inCity.top+"px","left":inCity.left+"px","width":inCity.width+"px","height":inCity.height+"px"});
}
inCity.place = function (e) {
    e.click(function(){
        placeThis = $(this);
        //城市显示
        inCity.cssParents();
        $(inCity.id).show();
        return false;
    })
}
inCity.destination = function (e){
    e.click(function(){
        placeThis = $(this);
        //城市显示
        inCity.cssParents();
        $(inCity.id).show();
        return false;
    })
}

/* 城市HTML模板 */
inCity._template = [
    '<h1>请选择城市</h1>',
    '<div class="screen">',
    '<a href="javascript:void(0)" class="shar">热门</a>',
    '<a href="javascript:void(0)">AB</a>',
    '<a href="javascript:void(0)">CD</a>',
    '<a href="javascript:void(0)">EFG</a>',
    '<a href="javascript:void(0)">HIJKL</a>',
    '<a href="javascript:void(0)">MNOPQ</a>',
    '<a href="javascript:void(0)">RST</a>',
    '<a href="javascript:void(0)">WXYZ</a>',
    '</div>',
    '<div class="city_pos" id="member-pet-sptype">',
    '<div class="city_a_le1">',
    '</div>',
    '<div class="city_a_le1" style="display: none">',
    '</div>',
    '<div class="city_a_le1" style="display: none">',
    '</div>',
    '<div class="city_a_le1" style="display: none">',
    '</div>',
    '<div class="city_a_le1" style="display: none">',
    '</div>',
    '<div class="city_a_le1" style="display: none">',
    '</div>',
    '<div class="city_a_le1" style="display: none">',
    '</div>',
    '<div class="city_a_le1" style="display: none">',
    '</div>',
    '</div>'
];
/* 所有城市数据,可以按照格式自行添加（北京|beijing|bj），前16条为热门城市 */

inCity.allCity = [ '德国牧羊犬|deguomuyangquan|dgmyq','博美犬|bomeiquan|bmq', '贵宾犬|guibinquan|gbq', '斗牛犬|douniuquan|dnq', '秋田犬|qiutianquan|qtq'
    , '阿富汗猎犬|afuhanliequan|afhlq', '边境牧羊犬|bianjingmuyangquan|bjmyq', '金毛寻回犬|jinmaoxunhuiquan|jmxhq','法国斗牛犬|faguodouniuquan|fgdnq'
    ,'阿拉斯加雪橇犬|alasijiaxueqiaoquan|alsjxqq','西伯利亚雪橇犬|xiboliyaxueqiaoquan|xblyxqq','吉娃娃|jiwawa|jww','中国沙皮犬|zhongguoshapiquan|zgspq', '松狮犬|songshiquan|ssq'
    ,'万能梗|wannenggeng|wng'
    , '美国爱斯基摩犬|meiguoaisijimoquan|mgasjmq', '美国斯塔福梗|meiguositafugeng|mgstfg'
    ,'美国水猎犬|meiguoshuiliequan|mgslq','安娜图牧羊犬|annatumuyangquan|antmyq', '澳洲牧牛犬|aozhoumuniuquan|azmnq'
    ,'澳洲梗|aozhougeng|azg', '贝吉生犬|beijishengquan|bjsq', '巴吉度猎犬|bajiduliequan|bjdlq', '比格犬|bigequan|bgq', '古代长须牧羊犬|gudaichangxumuyangquan|gdcxmyq'
    ,'贝德灵顿梗|beidelingdungeng|bdldg', '比利时马利诺斯牧羊犬|bilishimalinuosimuyangquan|blsmlnsmyq', '比利时牧羊犬|bilishimuyangquan|blsmyq'
    ,'比利时特弗伦牧羊犬|bilishitefulunmuyangquan|blstflmyq', '伯恩山犬|boenshanquan|besq', '卷毛比雄犬|juanmaobixiongquan|jmbxq', '黑褐猎浣熊犬|heiheliehuanxiongquan|hhlxq'
    ,'寻血猎犬|xunxueliequan|xxlq', '边境梗|bianjinggeng|bjg', '俄罗斯猎狼犬|eluosilielangquan|elsllq', '波士顿梗|boshidungeng|bsdg'
    ,'法兰德斯畜牧犬|falandesixumuquan|fldsxmq', '拳狮犬|quanshiquan|qsq', '伯瑞犬|baruiquan|brq', '不列塔尼猎犬|bulietaniliequan|bltnlq', '布鲁塞尔格里芬犬|bulusaiergelifenquan|blseglfq'
    ,'牛头梗|niutougeng|ntg', '斗牛獒|douniuao|dna', '卡南犬|kananquan|knq', '查理士王小猎犬|zhalishiwangxiaoliequan|clswxlq', '切萨皮克湾寻猎犬|qiesapikewanxunliequan|qspkwxlq'
    , '中国冠毛犬|zhongguoguanmaoquan|zggmq',  '克伦伯犬|kelunbaquan|klbq', '可卡犬|kekaquan|kkq', '苏格兰牧羊犬|sugelanmuyangquan|sglmyq'
    ,'卷毛寻回犬|juanmaoxunhuiquan|jmxhq', '腊肠犬|lachangquan|lcq', '斑点犬|bandianquan|bdq', '丹迪丁蒙梗|dandidingmenggeng|dddmg', '杜伯文犬|dubawenquan|dbwq', '阿根廷杜高犬|agentingdugaoquan|agtdgq'
    ,'英国可卡犬|yingguokekaquan|ygkkq', '英国塞特犬|yingguosaitequan|ygstq', '英国跳猎犬|yingguotiaoliequan|ygtlq', '英国玩具猎鹬犬|yingguowanjulieyuquan|ygwjlq', '田野猎犬|tianyeliequan|tylq'
    ,'芬兰狐狸犬|fenlanhuliquan|flhlq', '平毛巡回猎犬|pingmaoxunhuiliequan|pmxhlq', '猎狐梗|liehugeng|lhg', '刚毛猎狐梗|gangmaoliehugeng|gmlhg', '美国猎狐犬|meiguoliehuquan|mglhq', '英国猎狐犬|yingguoliehuquan|yglhq'
    ,'阿芬平嘉犬|afenpingjiaquan|afpjq', '德国短毛指示猎犬|deguoduanmaozhishiliequan|dgdmzslq', '德国硬毛指示猎犬|deguoyingmaozhishiliequan|dgymzslq', '大型史柔查梗|daxingshirouzhageng|dxsrcg'
    , '戈登塞特犬|gedengsaitequan|gdstq', '大丹犬|dadanquan|ddq', '大白熊犬|dabaixiongquan|dbxq', '大瑞士山地犬|daruishishandiquan|drssdq', '灵提犬|lingtiquan|ltq', '哈利犬|haliquan|hlq'
    ,'哈瓦那犬|hawanaquan|hwnq', '依比沙猎犬|yibishaliequan|ybslq', '爱尔兰塞特犬|aierlansaitequan|aelstq', '爱尔兰猎狼犬|aierlanlielangquan|aelllq', '爱尔兰梗|aierlangeng|aelg', '爱尔兰水猎犬|aierlanshuiliequan|aelslq'
    ,'意大利格雷猎犬|yidaligeleiliequan|ydlgllq', '杰克罗素梗|jiekeluosugeng|jklsg', '日本狆|ribenzhong|rbz', '荷兰毛狮犬|helanmaoshiquan|hlmsq', '凯利蓝梗|kaililangeng|kllg', '可蒙犬|kemengquan|kmq', '库瓦兹犬|kuwaziquan|kwzq'
    ,'拉布拉多巡回猎犬|labuladuoxunhuiliequan|lbldxhlq', '湖畔梗|hupangeng|hpg', '拉萨犬|lasaquan|lsq', '罗秦犬|luoqinquan|lqq', '玛尔济斯|maerjisi|mejs', '曼彻斯特梗|manchesitegeng|mcstg', '马士提夫犬|mashitifuquan|mstfq'
    ,'迷你牛头梗|mininiutougeng|mnntg', '迷你杜宾犬|minidubinquan|mndbq', '迷你史柔查|minishirouzha|mnsrc', '纽芬兰犬|niufenlanquan|nflq', '诺福克梗|nuofukegeng|nfkg'
    ,'挪威猎麋犬|nuoweiliemiquan|nwlq', '罗威士挭|luoweishigeng|lws', '古代英国牧羊犬|gudaiyingguomuyangquan|gdygmyq', '猎水獭犬|lieshuitaquan|lstq', '蝴蝶犬|hudiequan|hdq'
    ,'北京犬|beijingquan|bjq', '迷你贝吉格里芬凡丁犬|minibeijigelifenfandingquan|mnbjglffdq', '法老王猎犬|falaowangliequan|flwlq', '普罗特猎犬|puluoteliequan|pltlq'
    ,'波兰低地牧羊犬|bolandidimuyangquan|blddmyq', '指示犬|zhishiquan|zsq',  '葡萄牙水犬|putaoyashuiquan|ptysq'
    ,'八哥犬|bagequan|bgq', '波利犬|boliquan|blq', '罗得西亚脊背犬|luodexiyajibeiquan|ldxyjbq', '罗威纳犬|luoweinaquan|lwnq', '圣伯纳犬|shengbanaquan|sbnq', '萨卢基犬|salujiquan|sljq'
    ,'萨摩耶犬|samoyequan|smyq', '史其派克犬|shiqipaikequan|sqpkq', '苏格兰猎鹿犬|sugelanlieluquan|sglllq', '苏格兰梗|sugelangeng|sglg', '锡利哈姆梗|xilihamugeng|xlhmg'
    ,'喜乐蒂牧羊犬|xiledimuyangquan|xldmyq', '柴犬|chaiquan|cq', '西施犬|xishiquan|xsq',  '丝毛梗|simaogeng|smg'
    ,'斯开岛梗|sikaidaogeng|skdg', '爱尔兰软毛梗|aierlanruanmaogeng|aelrmg', '史毕诺犬|shibinuoquan|sbnq', '斯塔福郡斗牛梗|sitafujundouniugeng|stfjdng'
    ,'标准史柔查|biaozhunshirouzha|bzsrc', '苏塞克斯猎犬|susaikesiliequan|sskslq', '藏獒|zangao|za', '西藏猎犬|xizangliequan|xclq', '西藏梗|xizanggeng|xcg'
    ,'维兹拉犬|weizilaquan|wzlq', '威玛猎犬|weimaliequan|wmlq', '威尔士柯基犬卡地甘|weiershikejiquankadegan|weskjqkdg', '彭布洛克威尔斯柯基犬|pengbuluokeweiersikejiquan|pblkweskjq'
    ,'威尔士跳猎犬|weiershitiaoliequan|westlq', '威尔斯梗|weiersigeng|wesg', '西部高地白梗|xibugaodibaigeng|xbgdbg', '惠比特犬|huibitequan|hbtq'
    ,'刚毛指示格里芬犬|gangmaozhishigelifenquan|gmzsglfq', '约克夏|yuekexia|ykx'];

/* 正则表达式 筛选中文城市名、拼音、首字母 */

inCity.regEx = /^([\u4E00-\u9FA5\uf900-\ufa2d]+)\|(\w+)\|(\w)\w*$/i;
inCity.regExChiese = /([\u4E00-\u9FA5\uf900-\ufa2d]+)/;

(function () {
    var citys = inCity.allCity, match, letter,
        regEx = inCity.regEx,
        reg2 = /^[a-b]$/i,
        reg3 = /^[c-d]$/i,
        reg4 =  /^[e-g]$/i,
        reg5 = /^[h-l]$/i,
        reg6 = /^[m-q]$/i,
        reg7 = /^[r-t]$/i,
        reg8 = /^[w-z]$/i;
    if (!inCity.oCity) {
        inCity.oCity = {hot:{},AB:{},CD:{},EFG:{}, HIJKL:{}, MNOPQ:{},RST:{}, WXYZ:{}};
        //console.log(citys.length);
        for (var i = 0, n = citys.length; i < n; i++) {
            match = regEx.exec(citys[i]); //exec
            letter = match[3].toUpperCase(); //转换字母为大写

            if (reg2.test(letter)) { //test检测一个字符串是否匹配某个模式
                if (!inCity.oCity.AB[letter]) inCity.oCity.AB[letter] = [];
                inCity.oCity.AB[letter].push(match[1]);
            }else if (reg3.test(letter)) {
                if (!inCity.oCity.CD[letter]) inCity.oCity.CD[letter] = [];
                inCity.oCity.CD[letter].push(match[1]);
            }else if (reg4.test(letter)) {
                if (!inCity.oCity.EFG[letter]) inCity.oCity.EFG[letter] = [];
                inCity.oCity.EFG[letter].push(match[1]);
            }else if (reg5.test(letter)) {
                if (!inCity.oCity.HIJKL[letter]) inCity.oCity.HIJKL[letter] = [];
                inCity.oCity.HIJKL[letter].push(match[1]);
            } else if (reg6.test(letter)) {
                if (!inCity.oCity.MNOPQ[letter]) inCity.oCity.MNOPQ[letter] = [];
                inCity.oCity.MNOPQ[letter].push(match[1]);
            }else if (reg7.test(letter)) {
                if (!inCity.oCity.RST[letter]) inCity.oCity.RST[letter] = [];
                inCity.oCity.RST[letter].push(match[1]);
            } else if (reg8.test(letter)) {
                if (!inCity.oCity.WXYZ[letter]) inCity.oCity.WXYZ[letter] = [];
                inCity.oCity.WXYZ[letter].push(match[1]);
            }
            /* 热门城市 前16条 */
            if(i<16){
                if(!inCity.oCity.hot['hot']) inCity.oCity.hot['hot'] = [];
                inCity.oCity.hot['hot'].push(match[1]);
            }
        }
    }
})();
// 热门城市
inCity.Hot = function(cityA){
    var ckey,odda,sortKey,str,odda=[],abc=[],key,regEx = inCity.regEx,oCity = inCity.oCity,len,leni;
    for(key in oCity){
        sortKey=[];
        for(ckey in oCity[key]){
            sortKey.push(ckey);
            // ckey按照ABCDEDG顺序排序
            sortKey.sort();
        }
        for(var j=0,k = sortKey.length;j<k;j++){
            odda = [];
            abc=[];
            for(var i=0,n=oCity[key][sortKey[j]].length;i<n;i++){

                if(key == 'hot'){
                    $(inCity.id).find(inCity.Children).eq(0).append('<a href="javascript:void(0)">' + oCity[key][sortKey[j]][i] + '</a>');
                    odda.push(str);
                }else{
                    str = '<a href="javascript:void(0)">' + oCity[key][sortKey[j]][i] + '</a>';
                    inCity.arrRepeat(abc,sortKey,j); //获取字母
                    odda.push(str);
                    len = n;
                    leni = i;
                }

            }
            inCity.cityPinyin(leni,len,key,abc,odda);
        }
    }
}

// 按拼音排序
inCity.cityPinyin = function(leni,len,key,abc,odda){
    if(leni != undefined && key != 'hot'){
        if(len-1 == leni){
            var one;
            switch (key)
            {
                case 'AB':
                    one = 1;
                    break;
                case 'CD':
                    one = 2;
                    break;
                case 'EFG':
                    one = 3;
                    break;
                case 'HIJKL':
                    one = 4;
                    break;
                case 'MNOPQ':
                    one = 5;
                    break;
                case 'RST':
                    one = 6;
                    break;
                case 'WXYZ':
                    one = 7;
                    break;
            }
            $(inCity.id).find(inCity.Children).eq(one).append('<div class="Letter">'
                +'<span>'+abc[0]+'</span>'
                +'<div>'
                +odda.join('')
                +'</div>'
                +'</div>');
        }
    }
}

// 数组去重
inCity.arrRepeat = function(abc,sortKey,j){
    var nab = sortKey[j];
    for(var i in abc){
        if(abc[i] == nab){
            return nab = 1;
        }
    }
    if(nab != 1){
        abc.push(sortKey[j])
    }
}
// 城市切换
inCity.payment = function($this){
    var ind = $this.index();
    $this.siblings().removeClass("shar");
    $this.addClass("shar");
    $this.parent().next().children().hide();
    $this.parent().next().children().eq(ind).show();
}
// 给input赋值
inCity.cityClick = function ($this) {
    $(".city_a_le1 a").click(function () {
        var a_city = $(this).text();  //当前选择的城市
        $(inCity.id).hide();  //隐藏城市选择框 
        placeThis.val(a_city);  //赋值
        return false;
    })
}