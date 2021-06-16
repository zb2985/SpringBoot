<?php
$trans_text = $_POST['trans_text']; # AJAX비동기 통신으로 받은 데이터를 변수에 담는다
$client_id = "3_gqaAGqIO5b4lLHXhrD";
$client_secret = "DXqA0sX6q8";
$encText = urlencode($trans_text);  # 번역을 요청하는 값에 받은 데이터를 담은 변수를 넣는다
$postvars = "source=ko&target=en&text=".$encText;
$url = "https://openapi.naver.com/v1/papago/n2mt";
$is_post = true;
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_POST, $is_post);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch,CURLOPT_POSTFIELDS, $postvars);
$headers = array();
$headers[] = "X-Naver-Client-Id: ".$client_id;
$headers[] = "X-Naver-Client-Secret: ".$client_secret;
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
$response = curl_exec ($ch);
$status_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
$deco = json_decode($response,false);
curl_close ($ch);
if($status_code == 200) {
    print_r($deco->message->result->translatedText);
} else {
    echo "Error 내용:".$response;
}
?>

