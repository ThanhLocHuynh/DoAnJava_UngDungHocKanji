--create database Hoc_Kanji
--drop database Hoc_Kanji
use Hoc_Kanji

go

create table TaiKhoan(
	TaiKhoan char(30) primary key,
	MatKhau char(30),
	Ten nvarchar(50),
	Email nvarchar(255),
	Otp char(6)
)

create table Kanji(
	Id int  primary key IDENTITY(1,1),
	phienam nvarchar(255),
	hiragana nvarchar(255),
	mean nvarchar(255),
	kanji nvarchar(255),
)

go

create table DiemSo(
	TaiKhoan char(30) foreign key references TaiKhoan(TaiKhoan),
	TrinhDo char(3),
	Diem int,
	SoCau int,
	primary key(TaiKhoan,TrinhDo)
)

go

--Mat khau truoc khi ma hoa la 123456
insert into TaiKhoan VALUES ('HTL','142536',N'Huỳnh Thanh Lộc',N'thanhlochuynh102@gmail.com',null)
insert into TaiKhoan VALUES ('TDTL','142536',N'Trần Dương Tấn Lộc',N'tranduongtanloc2002@gmail.com',null)
insert into TaiKhoan VALUES ('VMT','142536',N'Võ Minh Thuận',N'thuanvo2523@gmail.com',null)

go

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('ichi',N'いち',N'Nhất',N'一')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('ni',N'に',N'Nhị',N'ニ')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('san',N'さん',N'Tam',N'三')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('yon',N'よん',N'Tứ',N'四')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('go',N'ご',N'Ngũ',N'五')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('roku',N'ろく',N'Lục',N'六')


insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('shichi',N'しち，なな',N'Thất',N'七')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('hachi',N'はち',N'Bát',N'八')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('kyuu',N'きゅう，く',N'Cửu',N'九')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('jyuu',N'じゅう',N'Thập',N'十')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('hyaku',N'ひゃく',N'Trăm',N'百')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('sen',N'せん',N'Ngàn',N'千')

insert into Kanji(phienam,hiragana,mean,kanji)
VALUES('man',N'まん',N'Vạn',N'万')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('en',N'えん',N'Viên',N'円')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('kuchi',N'くち',N'Khẩu',N'口')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('me',N'め',N'Mục',N'目')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('nichi',N'にち',N'Nhật',N'日')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('tsuki',N'つき',N'Nguyện',N'月')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('hi',N'ひ',N'Hỏa',N'火')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('mizu',N'みず',N'Thủy',N'水')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('ki',N'き',N'Mộc',N'木')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('kin',N'きん',N'Kim',N'金')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('do',N'ど',N'Thổ',N'土')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('you',N'よう',N'Diệu',N'曜')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('bon',N'ぼん，ほん',N'Bản',N'本')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('hito',N'ひと，じん',N'Nhân',N'人')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('ima',N'いま',N'Kim',N'今')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('tera',N'てら',N'Tự',N'寺')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('toki',N'とき',N'Thời',N'時')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('han',N'はん',N'Bán',N'半')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('katana',N'かたな',N'Đao',N'刀')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('bun',N'ぶん',N'Phân',N'分')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('ue',N'うえ',N'Thượng',N'上')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('shita',N'した',N'Hạ',N'下')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('naka',N'なか',N'Trung',N'中')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('soto',N'そと',N'Ngoại',N'外')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('migi',N'みぎ',N'Hữu',N'右')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('kou',N'こう，く，ぐ',N'Công',N'工')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('hidari',N'ひだり',N'Tả',N'左')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('mae',N'まえ',N'Tiền',N'前')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('ushiro',N'うしろ',N'Hậu',N'後ろ')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('uma',N'うま',N'Ngọ',N'生')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('mon',N'もん',N'Môn',N'門')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('aida',N'あいだ',N'Gian',N'間')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('higashi',N'ひがし',N'Đông',N'東')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('nishi',N'にし',N'Tây',N'西')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('minami',N'みなみ',N'Nam',N'南')

insert into Kanji (phienam,hiragana,mean,kanji)
VALUES('kita',N'きた',N'Bắc',N'着')

--select * from DiemSo where TaiKhoan = 'HTL'

--UPDATE DiemSo SET Diem = 100, TongLanChoi = 200 WHERE TaiKhoan = 'HTL' and TrinhDo = 'N3'

