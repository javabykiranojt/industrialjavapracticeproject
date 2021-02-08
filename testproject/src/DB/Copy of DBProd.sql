select this_.feedbackId as feedbackId4_3_, this_.userEmpId as userEmpId4_3_,
this_.taskId as taskId4_3_, this_.client_id as client4_4_3_,
this_.taskAssignProof as taskAssi5_4_3_, this_.feedbackDetails as feedback6_4_3_,
 this_.Status as Status4_3_, this_.createdDTM as createdDTM4_3_,
 this_.modifiedDTM as modified9_4_3_, this_.created_uId as created10_4_3_,
  this_.modified_uId as modified11_4_3_, this_.sent_status as sent12_4_3_,
  this_.sentDTM as sentDTM4_3_, this_.remarkByClient as remarkB14_4_3_,
  clients1_.client_id as client1_7_0_, clients1_.userEmpId as userEmpId7_0_,
  clients1_.client_userEmpId as client3_7_0_, clients1_.docId as docId7_0_,
  clients1_.client_name as client5_7_0_, clients1_.createdDTM as createdDTM7_0_,
   clients1_.modifiedDTM as modified7_7_0_, clients1_.created_uId as created8_7_0_,
   clients1_.modified_uId as modified9_7_0_, clients1_.docname as docname7_0_,
   userdetail2_.userEmpId as userEmpId12_1_, userdetail2_.locId as locId12_1_,
   userdetail2_.uId as uId12_1_, userdetail2_.firstName as firstName12_1_,
   userdetail2_.lastName as lastName12_1_, userdetail2_.address as address12_1_,
    userdetail2_.contactNo as contactNo12_1_, userdetail2_.emailId as emailId12_1_,
     userdetail2_.status as status12_1_, userdetail2_.gender as gender12_1_,
      userdetail2_.createdDTM as createdDTM12_1_, userdetail2_.modifiedDTM as modifie12_12_1_,
       userdetail2_.created_uId as created13_12_1_, userdetail2_.modified_uId as modified14_12_1_,
        userdetail2_.verificationId as verific15_12_1_, userdetail2_.expiryDate as expiryDate12_1_,
        users3_.uId as uId5_2_, users3_.uName as uName5_2_, users3_.uPass as uPass5_2_,
        users3_.uType as uType5_2_, users3_.createdDTM as createdDTM5_2_,
        users3_.modifiedDTM as modified6_5_2_, users3_.lastAccessedTime as lastAcce7_5_2_
        from user_feedback this_ inner join clients clients1_ on this_.client_id=clients1_.client_id
         inner join user_detail userdetail2_ on clients1_.userEmpId=userdetail2_.userEmpId
         inner join users users3_ on userdetail2_.uId=users3_.uId
where this_.sent_status='Y' and users3_.uId=9