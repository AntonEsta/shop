/**
 * 
 */

 let expendMenuItem = function(el,id){
    $.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			submenu: "",
			cat: id
		},
        success: function(data) {
            $(el).after(data);
        }
    });
}

let showCategoryGoods = function(id) {
	$.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			catbycat: "",
			cat: id
		},
        success: function(data) {
            $("#main-content").html(data);
        }
    });
}

let showCart = function(id) {
	$.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			cart: ""
		},
        success: function(data) {
            $("#main-content").html(data);
        }
    });
}

let clearPopup = function() {
	$("#registration-popup").remove();
	$("#signin-popup").remove();
}

let showRegForm = function(){
	$.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			regform: ""
		},
        success: function(data) {
			clearPopup();
            $("body").prepend(data);
        }
    });
}

let regUsr = function(){
	let name = $("#registration-popup-name").val();
	let email = $("#registration-popup-email").val();
	let pwd = $("#registration-popup-pwd").val();
	$.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			regform: "",
			name: name,
			email: email,
			pwd: pwd
		},
        success: function(data) {
			clearPopup();
            $("body").prepend(data);
        }
    });
}

let showSignInForm = function(){
	$.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			signin: ""
		},
        success: function(data) {
			clearPopup();
            $("body").prepend(data);
        }
    });
}

let signinUsr = function() {
	let email = $("#signin-popup-email").val();
	let pwd = $("#signin-popup-password").val();
	$.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			signin: "",
			email: email,
			pwd: pwd
		},
        success: function(data) {
			clearPopup();
            $("body").prepend(data);
        }
    });
}

let logOut = function(){
	$.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			logout: ""
		},
        success: function(data) {
			location.reload();
        }
    });
}

let goodToCart = function(id,count){
	$.ajax({
        url: "main",
        method: "GET",
        dataType: "text",
        data: {
			goodtocart: "",
			id: id,
			count: count
		},
        success: function(data) {
			$('body').append(data);
        }
    });
}


let hideRegForm = function() {
	$("#registration-popup").remove();
}

let hideSignInForm = function() {
	$("#signin-popup").remove();
}