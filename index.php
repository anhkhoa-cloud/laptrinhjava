<?php
require_once '../core/Database.php';
require_once '../core/Router.php';
session_start();
Router::route();