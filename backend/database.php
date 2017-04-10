<?php

include_once 'db.php';

 class DatabaseLayer{ 			
    
    public function __constructor(){
    }
    
    public function isUserExist($id, $phone){
        $dbObject = new DbConnect();
        $sql = "Select * from id_phone where id = $id and phone = $phone limit 1";
        $result = mysqli_query($dbObject->getDb(), $sql);
        if(count($result) > 0){
            return true;
        }
        return false;		
    }
    
    public function addNewUser($id, $phone){
        $dbObject = new DbConnect();
        if(!$this->isUserExist($id, $phone)){
            $sql = "Insert into id_phone (id, phone) values ('$id', '$phone')";
            $result = mysqli_query($dbObject->getDb(), $sql);
            if($result){
                return true;
            }
            return false;		
        }
    }
    
}
    
?>