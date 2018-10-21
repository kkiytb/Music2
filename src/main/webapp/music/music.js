/**
 * 音乐盒子代码
 */
var arr = $(".musicname");
$(function () {
	var arr = $(".musicname");
	showName();
});
function getMusicName(){
	var musicSrc = $("#musicaudio")[0].currentSrc;
	musicSrc = decodeURI(musicSrc);
	var musicName = musicSrc.substring(musicSrc.lastIndexOf("/") + 1);
	return musicName;
}
	
function nextOne(){
	var musicName = getMusicName();
	var musicSrc = $("#musicaudio")[0].currentSrc;
	for(var i = 0; i < arr.length; i++){
		if(arr[i].innerText == musicName && (i != arr.length -1)){
			$("#musicaudio").attr("src",musicSrc.substring(0,musicSrc.lastIndexOf("/")) + "/" + arr[i + 1].innerText);		
		} 
	}
	showName();
}
function playStatus(){
	var obj = event.target || event.srcElement;
	$(obj).toggleClass("glyphicon-play glyphicon-pause");
	if($("#musicaudio")[0].paused){
		$("#musicaudio")[0].play();
		return false;
	}
	if($("#musicaudio")[0].played){
		$("#musicaudio")[0].pause();
		return false;
	}
}
function prevOne(){
	var musicName = getMusicName();
	var musicSrc = $("#musicaudio")[0].currentSrc;
	for(var i = 0; i < arr.length; i++){
		if(arr[i].innerText == musicName && (i != 0)){
			$("#musicaudio").attr("src",musicSrc.substring(0,musicSrc.lastIndexOf("/")) + "/" + arr[i - 1].innerText);		
		} 
	} 
	showName();
}
var lastNumber;
function randomOne(){
	var musicName = getMusicName();
	var musicSrc = $("#musicaudio")[0].currentSrc;
	var number = parseInt(Math.random() * (arr.length) + 1);
	while(number == lastNumber){
		number = parseInt(Math.random() * (arr.length) + 1);
	}
	lastNumber = number;
	for(var i = 0; i < arr.length; i++){
		if(arr[i].innerText != musicName && ((number - i) >= 0 && (number - i) <= arr.length - 1)){
			/*console.log(number - i);*/
			$("#musicaudio").attr("src",musicSrc.substring(0,musicSrc.lastIndexOf("/")) + "/" + arr[number - 1].innerText);	
		} 
	} 
	showName();
}
function showName(){
	setTimeout(() => {
		var musicName = getMusicName();
		$("#mytitle").text(musicName);
		getName();
	}, 50);
}
var flag = false;
function thisOne(){
	var obj = event.target || event.srcElement;
	$(obj).toggleClass("glyphicon-repeat glyphicon-play-circle");
	if($("#musicaudio").attr("loop") != "loop"){
		$("#musicaudio").attr("loop","loop");
		$("#musicaudio").attr("onended","return false");
		$(obj).attr("title","单曲循环");
		flag = true;
		return false;
	}
	if(flag){
		$("#musicaudio").removeAttr("loop");
		$("#musicaudio").attr("onended","nextOne()");
		$(obj).attr("title","列表循环");
		flag = false;
	}
}
function play(){
	var obj = event.target || event.srcElement;
	var musicSrc = $("#musicaudio")[0].currentSrc;
	$("#musicaudio").attr("src",musicSrc.substring(0,musicSrc.lastIndexOf("/")) + "/" + $(obj).text());	
	showName();
}
function getName() {
	var musicName = getMusicName();
	for(var i = 0; i < arr.length; i++){
		if(arr[i].innerText == musicName){
			arr[i].style.animationPlayState = "running";
			arr[i].style.animationIterationCount = "infinite";
		} else{
			arr[i].style.animationIterationCount = "0";
		}
	}
}

