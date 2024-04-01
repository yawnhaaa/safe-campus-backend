CREATE DATABASE IF NOT EXISTS `safe_campus` DEFAULT CHARACTER SET utf8;

USE `safe_campus`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `id`        bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `stu_id`    varchar(20) NOT NULL COMMENT '学号',
    `name`      varchar(10) NOT NULL COMMENT '昵称',
    `email`     varchar(50) NOT NULL COMMENT '邮箱',
    `passwd`    varchar(64) NOT NULL COMMENT '密码',
    `salt`      varchar(64) NOT NULL COMMENT '盐',
    `intro`     varchar(200) COMMENT '个人介绍',
    `gender`    tinyint(1) COMMENT '性别',
    `school`    varchar(20) COMMENT '学校',
    `college`   varchar(20) COMMENT '学院',
    `stu_class` varchar(20) COMMENT '班级',
    `is_delete` tinyint(1)  NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin`
(
    `id`       bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` varchar(10) NOT NULL COMMENT '用户名',
    `passwd`   varchar(64) NOT NULL COMMENT '密码',
    `salt`     varchar(64) NOT NULL COMMENT '盐',
    PRIMARY KEY (`id`)
);

INSERT INTO admin (username, passwd, salt)
VALUES ('admin',
        'f5e90050be7738876e6cdbf9d0e912a9fcbda9d15a9c2bb2f15292ca0855c6f4',
        'f716c58d2f7e8d3967b210be30f50946d8f4c05964d59ae4bf7c4aeabaa19001');

DROP TABLE IF EXISTS `info`;

CREATE TABLE `info`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`        varchar(200) NOT NULL COMMENT '资讯标题',
    `author`       varchar(20)  NOT NULL COMMENT '资讯作者',
    `author_id`    bigint(20) DEFAULT 0 COMMENT '资讯作者主键, 外部资讯为0',
    `info_date`    DATE         NOT NULL COMMENT '资讯发布时间',
    `content`      TEXT         NOT NULL COMMENT '资讯内容',
    `img`          varchar(100) COMMENT '图片地址',
    `info_like`    int(10)    DEFAULT 0 COMMENT '点赞数',
    `info_collect` int(10)    DEFAULT 0 COMMENT '收藏数',
    `is_delete`    tinyint(1) DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);

INSERT INTO info (title, author, author_id, info_date, info_like, info_collect, content)
VALUES ('全国打击治理电信网络诈骗工作视频会议召开 统筹推进打防管控建各项工作 更好守护人民群众生命财产安全', '公安部', 0,
        '2024-3-31', 5222, 111,
        '	全国打击治理电信网络诈骗工作视频会议29日召开。会议强调，要以习近平新时代中国特色社会主义思想为指导，全面贯彻党的二十大和二十届二中全会精神，深入贯彻落实党中央、国务院决策部署，准确把握当前电信网络诈骗犯罪的新变化新趋势，以更加强烈的政治担当，统筹推进打防管控建各项工作，奋力夺取反诈人民战争新胜利，更好守护人民群众生命财产安全。
	会议指出，近年来，在党中央坚强领导下，各地各部门坚持以人民为中心的发展思想，突出党委领导、政府主导、部门主责、行业监管、社会参与，强化系统观念、法治思维，深化运用“四专两合力”总体思路，狠抓打防管控建各项措施落实，以空前力度严打电信网络诈骗犯罪，推动抓获移交一批缅北诈骗集团重要头目，彻底摧毁臭名昭著的果敢“四大家族”犯罪集团，取得一系列重大战果，赢得社会广泛赞誉。成绩的取得，根本在于习近平总书记掌舵领航，在于习近平新时代中国特色社会主义思想科学指引。
	会议强调，要在依法严打上下更大功夫。要深入开展“云剑”“断流”“拔钉”等专项行动，持续掀起打击高潮，不获全胜决不收兵。要积极作为、加大运筹，依托各类执法合作机制平台，明确细化工作标准，统筹推进双多边联合行动，不断在打击电信网络诈骗犯罪上取得实效。要强力推进重点攻坚，对个案损失巨大、社会影响突出、犯罪情节恶劣的重大案件，深入组织开展专案攻坚，确保打深打透打彻底；对组织偷渡犯罪，以“断流”“拔钉”专项行动为载体，深挖彻查、全链条打击，坚决斩断赴外作案通道；对“金主”、骨干，要综合采取有力措施，全力缉捕；对黑灰产窝点，深入开展研判，不间断发起区域会战、集群战役，最大限度挤压犯罪空间。
	会议指出，要在强化预防上下更大功夫。要精准开展预警劝阻，优化分级分类预警劝阻机制，综合采取上门见面、短信提醒、智能语音等方式，全力压案降损。要精确实施技术反制，深入研究诈骗通联渠道、洗钱方式、手法套路等，找准诈骗分子的命门和死穴，打造技术反制的“撒手锏”，有效提升通信拦截、网络封堵、资金拦截的实效。要精心组织宣传防范，组织开展无诈社区、无诈校园创建活动，营造全民反诈、全社会反诈浓厚氛围。
	会议强调，要在源头管控上下更大功夫。要用足用好法律武器和政策规定，加强对各类涉诈人员的预警发现，分类细化管控措施。要通过增强就业吸附能力、营造勤劳致富良好风气，教育转化涉诈人员，从源头上铲除电信网络诈骗犯罪的滋生土壤。要紧盯青少年思想动向，加强教育引导，防止其被诈骗集团蛊惑利用。
	会议指出，要在综合治理上下更大功夫。要紧盯金融领域，深入推进涉诈“资金链”治理，督促指导金融机构严格执行“实名实人实操”制度，加大执法检查和处罚力度，坚决堵住非法买卖、出租、出借账户的漏洞。要紧盯通信领域，深入开展“断卡”专项行动，重点整治滥发新卡、违规批量注册网络账号、群发诈骗短信等乱象，深化物联网卡、短信端口、宽带专线、接入设备等监管治理，推动落实实名制、黑名单、联合惩戒等制度，提升技术反制能力。要紧盯互联网领域，督促互联网企业落实主体责任，全面加强网络生态治理，组织开展涉诈网络黑灰产专项整治。
	会议要求，深入贯彻落实《中华人民共和国反电信网络诈骗法》和中共中央办公厅、国务院办公厅《关于加强打击治理电信网络诈骗违法犯罪工作的意见》，通过联合挂牌督办、联合督导等方式，进一步统一标准认识，强化法律适用，确保依法严厉惩处。要推动开展《中华人民共和国反电信网络诈骗法》实施情况的执法检查工作。要牢固树立“一盘棋”思想，加强资源整合、力量融合、协同配合，切实形成齐抓共管的工作合力。'),
       ('让防治走在网络诈骗前面（人民时评）', '人民网－人民日报', 0, '2024-3-30', 5020, 170, '	“群众能减少损失，我们打多少个预警电话、发送多少个预警短信都乐意”，在一位民警心声的背后，是2021年国家反诈中心APP累计向群众预警2.3亿次的务实行动；“未知链接不点击，陌生来电不轻信，个人信息不透露，转账汇款多核实”，在警示宣传的背后，是全民参与、全社会反诈的强大声势；2021年共破获电信网络诈骗案件44.1万余起，在数字的背后，是公安干警的不辞辛劳、不畏艰险。经过持续努力，打击整治电信网络诈骗的网络越织越密，有效保障着人民群众的获得感、幸福感、安全感。
　　电信网络诈骗是人民群众深恶痛绝的新型网络犯罪。习近平总书记对打击治理电信网络诈骗犯罪工作作出重要指示强调，“坚决遏制此类犯罪多发高发态势，为建设更高水平的平安中国、法治中国作出新的更大的贡献”。近年来，除了公安机关不断加大打击电信网络诈骗力度之外，工信部部署开展“断卡2.0”专项行动，清理涉诈电话卡；人民银行开展行动，清理整治涉诈银行账户；中央网信办全力加强网络技术反制，封堵涉诈网址；各地党委政府切实加强源头治理，严防形成诈骗窝点，强力清除涉诈黑灰产。各地区各部门贯彻党中央决策部署，持续开展电信网络诈骗犯罪打击治理，取得了阶段性成效。
　　实践证明，打击治理电信网络诈骗犯罪不能止于见招拆招，必须让防治走在网络诈骗前面。这些年来，骗术套路在不断进化。而且，电信网络诈骗背后隐藏着一条庞大的利益链条。上游非法收集办理电话卡、银行卡，提供公民个人信息、诈骗剧本，搭建技术平台，中游通过引诱、恐吓等办法将目标引入圈套，下游为诈骗集团转赃取现。尤其许多诈骗分子藏匿在境外，不同国家对电诈的监管力度、法律规则存在差异，造成我国警方取证难、遣返难、打击难。这些都是诈骗犯罪打击治理工作任务艰巨繁重之所在，更加需要强化系统观念、法治思维，注重源头治理、综合治理，坚持齐抓共管、群防群治。
　　然而，困难再大，大不过决心。目前，从国务院部级联席会议机制的制度设计，到反电信网络诈骗法草案的法治支撑；从公安、金融、通信、互联网等相关各方守土尽责的工作责任闭环，到广大人民群众识骗防骗能力显著提升，我们业已形成上下联动、相互支撑的全国一体化打击诈骗犯罪新格局，构建起全民反诈防骗新格局，为守护好群众“钱袋子”夯实了基础。只要坚持以人民为中心，全面落实打防管控各项措施，不断提高反诈防骗的科技含量、智慧含量、创新含量，我们就能提升社会综合治理水平。
　　再高明的网络诈骗技术，也是利用人性弱点。我们在紧盯诈骗犯罪手法新变化、创新打击之策的同时，还要着力营造诚实守信、勤劳致富的良好风气，引导全社会关注关爱触网较少的老人、涉世未深的年轻人。唯有如此，才能铲除滋生电信网络诈骗犯罪的土壤。'),
       ('【反电诈】深化源头治理 提高打击效能 筑牢反诈防线', '呼伦贝尔市公安局新闻宣传科', 0, '2024-1-21', 3333, 89, '	当前，随着互联网经济和电信产业的迅猛发展，传统犯罪加快向网上蔓延变异，以电信网络诈骗为代表的新型违法犯罪持续高发多发，已成为上升最快、群众反映最强烈的突出刑事犯罪。呼伦贝尔市公安机关深入贯彻党中央决策部署，对标对表“五个一流”工作要求，积极构建“一个体系、两个机制、三个关键”的工作体系。今年以来，全市共破电诈类案件751起，抓获犯罪嫌疑人1920人，已连续18个月实现公安部提出的“两升两降”的工作目标。
坚持党政主导，高规格构建“一个体系”
	面对严峻复杂的电信网络诈骗案件形势，呼伦贝尔市公安机关深入贯彻落实中共中央办公厅、国务院办公厅印发的《关于加强打击治理电信网络诈骗违法犯罪工作的意见》，立足呼伦贝尔市“城、农、林、牧、边、矿、水、草”地域特色，全力构建“党委领导、政府主导、部门主责、行业监管、有关部门齐抓共管、社会各界广泛参与”的多元共治的反诈工作体系。
	市委副书记、政法委书记奇·达楞太任组长，市委常委、宣传部、市公安局、市人民检察院、市中级人民法院等部门主要负责人任副组长，市委网信办、市工信局、中国人民银行呼伦贝尔市中心支行等47个成员单位组成的工作领导小组，统筹全市资源力量，深化推进打击治理工作；各旗市区比照市本级成立了相应组织领导机构，全面整合各方面资源，广泛凝聚打击治理整体合力。
	坚持双向发力，高效率用好“两个机制”
	紧紧围绕“四专两合力”理念，充分发挥市级联席会议制度和全市公安机关打击治理电信网络诈骗犯罪工作机制优势，整合全市资源，构建完善职责清晰、协同联动、衔接紧密、运转高效的打击治理机制。坚持从严打击电信网络诈骗犯罪、构建社会反诈防范体系、强化属地管控和源头治理、健全完善工作体系四个方面，出台13项具体措施，逐项明确了责任单位。三大通讯运营商、8家商业银行、15家互联网企业携最高权限入驻市公安局反诈中心，进一步优化联动工作机制，在精准拦截、快速止付、全力挽损方面成效显著。统筹各警种资源手段，持续推进反诈中心软硬件建设。
	组建预警核查、见面劝阻督导、反诈宣传三个专班，积极整合系统内资源优势，全面提升反诈预警劝阻、宣传防范工作合力。全力开展反诈宣传进社区、进农村、进家庭、进学校、进企业活动。构建了传统方式+新型模式、自媒体平台+外媒途径的全方位、广覆盖的反诈宣传体系。充分利用微信使用人员多、覆盖范围广的特点，建立了自治区首个盟市级反诈宣传“四级微信群”矩阵，覆盖人数165.86万人次，每天推送“反电诈即时资讯”，通报发案、介绍案例、提供建议，全面营造网络反诈宣传的浓厚氛围。
	同时，全力推广国家反诈中心APP安装工作，截至目前，全市国家反诈中心APP安装注册150余万人，注册率达70.56%，持续排名全区第一。针对易上当受骗群体和已被劝阻的易受骗人员等重点群体，发动全市警力下沉社会面、全警入户进行“精准滴灌”。截至目前，共制作、发放宣传折页217万份；联合教育部门开展“开学反诈第一课”，通过“小手拉大手”带动其家长一起参与反诈宣传教育，并在全部高校建立校园反诈中心，完善高校在校生兼职管理制度，做到了“大中小”学校全覆盖。
	坚持系统观念，高标准抓实“三个关键”
	紧紧围绕“严打、防范、治理”三个关键环节，采取有力的举措，整合各方资源，发动各方力量，坚决遏制电诈类案件高发势头，以“云剑”“百日行动”“双清双打”等专项行动为抓手，坚持以“破案、抓人、挽损、追刑”为目标，构建“全链条”打击体系。
	2022年，全市共发50万案件15起，现已全部破获。其中，海拉尔“8.08”特大电信诈骗案，抓获犯罪嫌疑人32名，挽损50余万元，并直接锁定了境外窝点犯罪嫌疑人；同时，抽调精干力量组建“蒙e突击队”，分区域精准投放警力、高位推进全链条打击的工作模式，实现以快制快、研判抓捕无缝衔接，推动打击能力提效升级，至今已组织三次集中外出抓捕，累计派出警力200余人。9月16日，仅用十小时就破获了虚假贷款诈骗案，挽回群众损失32万余元，再次创造了发案十小时就破获案件的“蒙e”速度。
	围绕“网络犯罪、网络防范、诈骗种类、受骗群体”工作方针，坚持打防结合、防范为先，最大限度地预防和减少电信网络诈骗违法犯罪发生。与有关部门科技公司深度合作，全面整合公安部电诈平台、公安厅电诈平台预警数据，形成了“数据下发+上门劝阻+督导回访+跟踪问效”全链条闭环反诈预警劝阻工作模式。
	自2021年8月至今，全市213个派出所累计接收紧急、中、高预警劝阻数据 40.06万人，成功预警劝阻 37.74万人次，见面率达到 94%，成功劝阻拦截止损 966.59万元，全市有53个派出所实现辖区电诈案件零发案。
	打击电信网络诈骗，是一场勠力同心的阵地战，也是一场维护诚信的攻坚战，更是一场守护群众安宁的保卫战。呼伦贝尔市公安机关将牢记为民初心，勇挑重担，为创建全民反电诈新格局，为建设“平安呼伦贝尔”持续贡献力量，以优异成绩迎接党的二十大胜利召开！');

DROP TABLE IF EXISTS `info_user`;

CREATE TABLE `info_user`
(
    id           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    name         varchar(10)  NOT NULL COMMENT '用户昵称',
    info_id      bigint(20)   NOT NULL COMMENT '资讯id',
    info_user_id varchar(100) NOT NULL COMMENT '复合id',
    is_like      tinyint(1) DEFAULT 0 COMMENT '是否喜欢',
    like_time    datetime COMMENT '喜欢时间',
    is_collect   tinyint(1) DEFAULT 0 COMMENT '是否收藏',
    collect_time datetime COMMENT '收藏时间',
    UNIQUE KEY `unique_info_user_id` (`info_user_id`),
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment`
(
    id             bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    info_id        bigint(20)   NOT NULL COMMENT '资讯id',
    comment        varchar(100) NOT NULL COMMENT '评论内容',
    comment_id     bigint(20)   NOT NULL COMMENT '评论人id',
    comment_name   varchar(10)  NOT NULL COMMENT '评论人昵称',
    commented_id   bigint(20) COMMENT '被评论人id',
    commented_name varchar(10) COMMENT '被评论人昵称',
    top_id         bigint(20) COMMENT '顶级评论id',
    comment_time   datetime     NOT NULL COMMENT '评论时间',
    is_delete      tinyint(1) DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material`
(
    id            bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    src           varchar(100) NOT NULL COMMENT '素材路径',
    img_src       varchar(100) COMMENT '列表图片路径',
    title         varchar(20)  NOT NULL COMMENT '素材标题',
    author        varchar(10)  NOT NULL COMMENT '作者',
    author_id     bigint(20) DEFAULT 0 COMMENT '作者id',
    material_date datetime     NOT NULL COMMENT '发布日期',
    material_type tinyint(1)   NOT NULL COMMENT '素材类型：0图、1视频、2音频',
    download      int(10)    DEFAULT 0 COMMENT '下载次数',
    is_delete     tinyint(1) DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);

INSERT INTO material (src, img_src, title, author, author_id, material_date, material_type)
VALUES ('image1.jpeg', 'image1.jpeg', '警惕红包返利骗局', '反诈中心', '0', '2024-03-31 13:08:42', 0),
       ('image2.jpeg', 'image2.jpeg', '警方支招·预防诈骗', '反诈中心', '0', '2024-03-28 13:08:42', 0),
       ('image3.jpeg', 'image3.jpeg', '全民反诈，诚信无诈；以信为桥，传递温暖', '反诈中心', '0', '2024-03-29 13:08:42', 0),
       ('image4.jpeg', 'image4.jpeg', '反诈有你，"视"不可挡', '反诈中心', '0', '2024-03-25 13:08:42', 0),
       ('image5.jpeg', 'image5.jpeg', '防范电信诈骗', '反诈中心', '0', '2024-03-22 13:08:42', 0),
       ('video1.mp4', 'video1.jpg', '防诈风云', '反诈视频中心', '0', '2024-03-27 13:08:42', 1),
       ('video2.mp4', 'video2.jpg', '你以为的反诈', '反诈视频中心', '0', '2024-03-31 13:08:42', 1),
       ('video3.mp4', 'video3.jpg', '防诈民警想破了头！', '反诈视频中心', '0', '2024-03-25 13:08:42', 1),
       ('audio1.mp3', 'audio1.jpeg', '市民反诈请万般注意', '反诈音频中心', '0', '2024-03-31 13:08:42', 2),
       ('audio2.mp3', 'audio2.jpeg', '信息反诈重中之重', '反诈音频中心', '0', '2024-03-24 13:08:42', 2),
       ('audio3.mp3', 'audio3.jpeg', '诈骗手法日日新，不听不信不贪心', '反诈音频中心', '0', '2024-04-2 13:08:42', 2);



DROP TABLE IF EXISTS `question_content`;
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`
(
    id        bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    type      tinyint(1)   NOT NULL COMMENT '题目类型：0单选、1多选',
    title     varchar(200) NOT NULL COMMENT '题目',
    analysis  varchar(500) NOT NULL COMMENT '解析',
    is_submit boolean    DEFAULT FALSE COMMENT '是否提交',
    is_delete tinyint(1) DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);
CREATE TABLE `question_content`
(
    id          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    question_id bigint(20)   NOT NULL COMMENT '外键',
    content     varchar(200) NOT NULL COMMENT '选项内容',
    is_checked  boolean    DEFAULT FALSE COMMENT '是否选择',
    is_true     boolean    DEFAULT FALSE COMMENT '是否正确',
    is_delete   tinyint(1) DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
);

INSERT INTO question (type, title, analysis)
VALUES (0, '电信网络诈骗受害人数最多的群体是？',
        '据国家反诈中心公布数据显示，在2021年电信网络诈骗的受害者中，18至35岁占比65.5%。年轻人才是电信网络诈骗的主要受害群体。'),
       (0,
        '居住在安庆市大观区的老王吃完晚饭正在客厅看电视，12岁的儿子小明忽然说要拿他手机上网买一本参考资料，过了几分钟，小明说已经选好了资料，要他刷脸付款。老王赶紧用手捂住了脸，并责令小明把手机关机。老王的做法对吗？',
        '骗子先用免费赠送游戏皮肤吸引小孩子，然后忽悠他们去借父母的手机扫付款二维码，并让父母刷脸支付。请各位家长千万不要随意告知子女自己的支付密码，也不要随意答应刷脸支付。'),
       (1,
        '安庆市大观区的马大姐接到了一个自称是大观区公安局民警的电话，在接下来的通话中，这个“民警”不断让马大姐按照要求做一些事，请问下面哪些事充满了风险？',
        '冒充公检法虽然是老骗术，但是招数却经常翻新，以上四类都是犯罪团伙经常使用的手法，请大家一定提高警惕。'),
       (0,
        '就读于安庆某校的小萌今年23岁，马上大四毕业，但却找不到合适的工作。有个同学告诉他，只要去办理银行卡、电话卡，然后申办营业执照和对公账户，每办一套卖给别人转账就可以得到3000元奖励金，如果协助处理后续解冻等事宜，酬劳翻倍。小萌觉得找到了一份好工作。请问小萌的做法可能会引发什么后果？',
        '为他人提供支付结算工具的行为，触犯了刑法第287条帮助信息网络犯罪活动罪。所有快速赚钱的办法都写在《刑法》里，比找不到工作更可怕的，是找到一份走向监狱的工作。'),
       (0, '我们常说的“杀猪盘”指的是',
        '杀猪盘是婚恋投资类诈骗的统称。骗子先伪装身份与受害人谈情说爱，随后再拉着受害人去投做各类投资。骗子把那些感情寂寞的人叫做猪，把建立恋爱关系的过程叫做养猪，把最后的诈骗环节叫做杀猪。'),
       (0,
        '目前东南亚部分国家已经成为国内诈骗分子行骗的天堂，他们通过偷渡方式从云南到接壤的部分国家，逃避国内警方的追踪和打击，其中缅甸的民地武控制地区是不少骗子的心水之选。那么，你认为从云南边境偷渡到缅甸民地武控制地区需要多少钱？',
        '蛇头利用云南边境特殊的地理形势和民地武地区特殊的管理体制，只需200元就能成功带人偷渡，让国内警察望线（边境线）兴叹。'),
       (0,
        '居住在安庆市大观区的刘小姐半个月前在网上认识了一个香港工程师jeff并坠入爱河，jeff说他掌握了一个赌博网站的漏洞，要求一起投注致富。一个星期左右，刘小姐输了20多万，jeff也消失不见。刘小姐觉得对方有问题，但是觉得自己的行为是赌博，便不敢去报案。请问刘小姐的想法是否正确？',
        '这属于典型的杀猪盘，应立即去报案。但如果不是杀猪盘，确实涉嫌网络赌博，也将接受相应的处罚。所以，在此提醒所有人：远离任何赌博，特别是网络赌博，不是被骗就是被抓。'),
       (1,
        '电信诈骗已经成为一个完善的产业链条，骗子之间的分工也越来越明确，诈骗界的“黑话”语系也逐渐形成。下列诈骗界的“黑话”与解释对应正确的是：',
        '目前已知的诈骗界黑话有几十个之多，懂5个就算是反诈骗专家！');

INSERT INTO question_content (question_id, content, is_true)
VALUES (1, '年轻人 ', true),
       (1, '中年人', false),
       (1, '老年人', false),
       (2, '对', true),
       (2, '错', false),
       (3, '让马大姐马上订一间房间有电脑能上网的民宿，把自己反锁好', true),
       (3, '让马大姐购买一部三星A8手机', true),
       (3, '让马大姐下载“蝙蝠”聊天软件，并在事后使用一键清理功能清除双方聊天记录', true),
       (4, '成为白富美，走上人生巅峰', false),
       (4, '成为阶下囚，在铁窗中度过人生最美的时期', true),
       (4, '成为就业先锋，在毕业典礼上发言', false),
       (5, '屠夫用的餐盘', false),
       (5, '杀猪之前先盘一下猪', false),
       (5, '婚恋投资类诈骗', true),
       (6, '2000人民币左右', false),
       (6, '2万人民币左右', false),
       (6, '200人民币左右', true),
       (7, '正确，报警的话自己也会被抓', false),
       (7, '错误，应该立即报警 ', true),
       (8, '料：银行卡四件套信息', true),
       (8, '水房：专门的洗钱集团', true),
       (8, '菠菜：博彩的谐音，一般指博彩业', true),
       (8, '猪蹄：主推的谐音，指博彩中介', true);

