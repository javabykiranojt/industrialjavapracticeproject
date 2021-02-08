delimiter //

create trigger user_detail_trigger_update 

after update

on user_detail

for each row

begin
   
   if(old.firstName!=new.firstName) then

    insert into history(dtm,old_value,new_value,function,column_name,uid)
     values(NOW(),old.firstName,new.firstName,'USER','firstName',new.modified_uid);

   end if;

   if(old.lastName!=new.lastName) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.lastName,new.lastName,'USER','lastName',new.modified_uid);

   end if;

   if(old.address!=new.address) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.address,new.address,'USER','address',new.modified_uid);

   end if;

   if(old.contactNo!=new.contactNo) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.contactNo,new.contactNo,'USER','contactNo',new.modified_uid);

   end if;

   if(old.emailId!=new.emailId) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values
    (NOW(),old.emailId,new.emailId,'USER','emailId',new.modified_uid);

   end if;

   if(old.status!=new.status) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.status,new.status,'USER','status',new.modified_uid);

   end if;

end//




delimiter //

create trigger user_detail_trigger_insert 
after insert
on user_detail

for each row

begin
   
	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.firstName,'USER','firstName',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.lastName,'USER','lastName',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.address,'USER','address',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.contactNo,'USER','contactNo',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.emailId,'USER','emailId',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.status,'USER','status',new.created_uId);
end//



delimiter //

create trigger tasks_trigger_update 

after update

on tasks
for each row

begin
   
  if(old.scheduleDate!=new.scheduleDate) then

  insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.scheduleDate,new.scheduleDate,'TASK','scheduleDate',new.modified_uid);

  end if;

 if(old.time!=new.time) then

insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.time,new.time,'TASK','time',new.modified_uid);

end if;

if(old.venue!=new.venue) then

   insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.venue,new.venue,'TASK','venue',new.modified_uid);

  end if;
 if(old.userEmpId!=new.userEmpId) then

insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.userEmpId,new.userEmpId,'TASK','userEmpId',new.modified_uid);

  end if;
  if(old.status!=new.status) then

   insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.status,new.status,'TASK','status',new.modified_uid);

end if;
end//


delimiter //

create trigger tasks_trigger_insert
after insert
on tasks
for each row

begin
   
    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.scheduleDate,'TASK','scheduleDate',new.created_uId);
    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.time,'TASK','time',new.created_uId);

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.venue,'TASK','venue',new.created_uId);

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.userEmpId,'TASK','userEmpId',new.created_uId);
    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.status,'TASK','status',new.created_uId);
  
end//



delimiter //


create trigger user_feedback_trigger_update


after update


on user_feedback

for each row


begin

   
if(old.taskAssignProof!=new.taskAssignProof) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.taskAssignProof,new.taskAssignProof,'FEEDBACK','taskAssignProof',new.modified_uid);

   end if;

   
if(old.feedbackDetails!=new.feedbackDetails) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.feedbackDetails,new.feedbackDetails,'FEEDBACK','feedbackDetails',new.modified_uid);

   end if;

   
if(old.status!=new.status) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.status,new.status,'FEEDBACK','status',new.modified_uid);

   end if;


end//


delimiter //

create trigger user_feedback_trigger_insert
after insert
on user_feedback
for each row

begin

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.taskAssignProof,'FEEDBACK','taskAssignProof',new.created_uId);

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.feedbackDetails,'FEEDBACK','feedbackDetails',new.created_uId);

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.status,'FEEDBACK','status',new.created_uId);

end//

 delimiter //

create trigger user_rewards_trigger_update
after update
on user_rewards
for each row
begin

if(old.point!=new.point) then
  insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.point,new.point,'REWARDS','point',new.modified_uid);
   end if;
if(old.redeem_point_req!=new.redeem_point_req) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.redeem_point_req,new.redeem_point_req,'REWARDS','redeem_point_req',new.modified_uid);

   end if;
   if(old.mode_of_payment!=new.mode_of_payment) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.mode_of_payment,new.mode_of_payment,'REWARDS','mode_of_payment',new.modified_uid);

   end if;
end
//

