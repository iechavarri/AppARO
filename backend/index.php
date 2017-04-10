<?php
require 'vendor/autoload.php';
include_once 'database.php';

$app = new \Slim\Slim();

$app->get('/:id/:phone', function($id, $phone){
    
//    $password = md5($password);
    
    $databaseObject = new DatabaseLayer();
    $isRegistered = $databaseObject->isUserExist($id, $phone);
    
    $app = \Slim\Slim::getInstance();
    
    if($isRegistered){
        $app->response->setStatus('200');	
        $app->response->headers->set('Content_Type', 'application/json');	
        echo json_encode(Array('isLogin' => '1'));
        
    }else{
        echo json_encode(Array('isLogin' => '0'));
    }
    
});

$app->post('/:id/:phone', function($id, $phone){
   // $password = md5($password);
    
    $app = \Slim\Slim::getInstance();
    
    $databaseObject = new DatabaseLayer();
    $isInserted = $databaseObject->addNewUser($id, $phone);
    
    if($isInserted){
        $app->response->setStatus('200');	
        $app->response->headers->set('Content_Type', 'application/json');	
        echo json_encode(Array('isLogin' => '1'));
    }else{
        echo json_encode(Array('isLogin' => '0'));
    }
 
});

$app->run();

?>