/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/3 14:19:23                            */
/*==============================================================*/


drop table if exists addresses;

drop table if exists admins;

drop table if exists comment;

drop table if exists counts;

drop table if exists give;

drop table if exists good_more;

drop table if exists goods;

drop table if exists kinds;

drop table if exists orders;

drop table if exists owner_count;

drop table if exists rider;

drop table if exists rider_income;

drop table if exists shops;

drop table if exists users;

drop table if exists youhui;

/*==============================================================*/
/* Table: addresses                                             */
/*==============================================================*/
create table addresses
(
   address_no           varchar(20) not null,
   user_no              varchar(20),
   sheng                varchar(20) not null,
   shi                  varchar(20) not null,
   address              varchar(20) not null,
   call_user            varchar(20) not null,
   address_tele         varchar(20) not null,
   primary key (address_no)
);

/*==============================================================*/
/* Table: admins                                                */
/*==============================================================*/
create table admins
(
   admin_no             varchar(20) not null,
   admin_name           varchar(20) not null,
   pwd                  varchar(20) not null,
   primary key (admin_no)
);

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   good_no              varchar(20) not null,
   user_no              varchar(20) not null,
   comment_word         varchar(1000) not null,
   comment_date         timestamp not null,
   comment_level        bigint not null,
   comment_pic          longblob not null,
   primary key (good_no, user_no)
);

/*==============================================================*/
/* Table: counts                                                */
/*==============================================================*/
create table counts
(
   count_no             varchar(20) not null,
   shop_no              varchar(20),
   ac_money             double not null,
   count_sale           double not null,
   together             bool not null,
   primary key (count_no)
);

/*==============================================================*/
/* Table: give                                                  */
/*==============================================================*/
create table give
(
   user_no              varchar(20) not null,
   shop_no              varchar(20) not null,
   need                 smallint not null,
   already              bigint not null,
   primary key (user_no, shop_no)
);

/*==============================================================*/
/* Table: good_more                                             */
/*==============================================================*/
create table good_more
(
   order_no             varchar(20) not null,
   good_no              varchar(20) not null,
   good_more            bigint not null,
   good_price           double not null,
   good_sale            double,
   primary key (order_no, good_no)
);

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   good_no              varchar(20) not null,
   kind_no              varchar(20),
   good_name            varchar(20) not null,
   good_price           double not null,
   good_sale            double not null,
   primary key (good_no)
);

/*==============================================================*/
/* Table: kinds                                                 */
/*==============================================================*/
create table kinds
(
   kind_no              varchar(20) not null,
   shop_no              varchar(20),
   kind_name            varchar(20) not null,
   kind_sum             bigint not null,
   primary key (kind_no)
);

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   order_no             varchar(20) not null,
   address_no           varchar(20),
   rider_no             varchar(20),
   count_no             varchar(20),
   shop_no              varchar(20),
   youhui_no            varchar(20),
   money                double not null,
   true_money           double not null,
   order_time           timestamp not null,
   arrive               timestamp not null,
   site                 bigint not null,
   primary key (order_no)
);

/*==============================================================*/
/* Table: owner_count                                           */
/*==============================================================*/
create table owner_count
(
   youhui_no            varchar(20) not null,
   user_no              varchar(20) not null,
   count_money          double not null,
   num                  bigint not null,
   end_date             timestamp not null,
   primary key (youhui_no, user_no)
);

/*==============================================================*/
/* Table: rider                                                 */
/*==============================================================*/
create table rider
(
   rider_no             varchar(20) not null,
   ride_name            varchar(20) not null,
   rider_start          timestamp not null,
   rider_level          bigint not null,
   primary key (rider_no)
);

/*==============================================================*/
/* Table: rider_income                                          */
/*==============================================================*/
create table rider_income
(
   order_no             varchar(20),
   rider_income_date    timestamp not null,
   rider_comment        bigint not null,
   income               double not null
);

/*==============================================================*/
/* Table: shops                                                 */
/*==============================================================*/
create table shops
(
   shop_no              varchar(20) not null,
   shop_name            varchar(20) not null,
   level                bigint not null,
   avg_consume          double not null,
   sum_sale             bigint not null,
   primary key (shop_no)
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   user_no              varchar(20) not null,
   user_name            varchar(20) not null,
   sex                  bool not null,
   pwd                  varchar(20) not null,
   tele                 varchar(20) not null,
   email                varchar(100) not null,
   city                 varchar(20) not null,
   sign_date            timestamp not null,
   vip                  bool not null,
   vip_end              timestamp,
   primary key (user_no)
);

/*==============================================================*/
/* Table: youhui                                                */
/*==============================================================*/
create table youhui
(
   youhui_no            varchar(20) not null,
   shop_no              varchar(20),
   youhui_sale          double not null,
   request              bigint not null,
   startday             timestamp not null,
   endday               timestamp not null,
   primary key (youhui_no)
);

alter table addresses add constraint FK_have5 foreign key (user_no)
      references users (user_no) on delete restrict on update restrict;

alter table comment add constraint FK_comment foreign key (good_no)
      references goods (good_no) on delete restrict on update restrict;

alter table comment add constraint FK_comment2 foreign key (user_no)
      references users (user_no) on delete restrict on update restrict;

alter table counts add constraint FK_have4 foreign key (shop_no)
      references shops (shop_no) on delete restrict on update restrict;

alter table give add constraint FK_give foreign key (user_no)
      references users (user_no) on delete restrict on update restrict;

alter table give add constraint FK_give2 foreign key (shop_no)
      references shops (shop_no) on delete restrict on update restrict;

alter table good_more add constraint FK_good_more foreign key (order_no)
      references orders (order_no) on delete restrict on update restrict;

alter table good_more add constraint FK_good_more2 foreign key (good_no)
      references goods (good_no) on delete restrict on update restrict;

alter table goods add constraint FK_have2 foreign key (kind_no)
      references kinds (kind_no) on delete restrict on update restrict;

alter table kinds add constraint FK_have foreign key (shop_no)
      references shops (shop_no) on delete restrict on update restrict;

alter table orders add constraint FK_have10 foreign key (youhui_no)
      references youhui (youhui_no) on delete restrict on update restrict;

alter table orders add constraint FK_have11 foreign key (address_no)
      references addresses (address_no) on delete restrict on update restrict;

alter table orders add constraint FK_have6 foreign key (shop_no)
      references shops (shop_no) on delete restrict on update restrict;

alter table orders add constraint FK_have8 foreign key (rider_no)
      references rider (rider_no) on delete restrict on update restrict;

alter table orders add constraint FK_have9 foreign key (count_no)
      references counts (count_no) on delete restrict on update restrict;

alter table owner_count add constraint FK_owner_count foreign key (youhui_no)
      references youhui (youhui_no) on delete restrict on update restrict;

alter table owner_count add constraint FK_owner_count2 foreign key (user_no)
      references users (user_no) on delete restrict on update restrict;

alter table rider_income add constraint FK_have12 foreign key (order_no)
      references orders (order_no) on delete restrict on update restrict;

alter table youhui add constraint FK_to foreign key (shop_no)
      references shops (shop_no) on delete restrict on update restrict;

