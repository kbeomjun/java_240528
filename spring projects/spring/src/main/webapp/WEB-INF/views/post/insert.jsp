<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
    <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.6.0/uicons-bold-rounded/css/uicons-bold-rounded.css'>
    <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.6.0/uicons-bold-straight/css/uicons-bold-straight.css'>
    
    <style type="text/css">
    	.img-container{
    		min-height: 400px;
    	}
    	.btn-insert-img{
    		line-height: 21px; width: 42px; height: 38px; border-radius: 50%; padding: 8px;
    	}
    	.btn-delete-img{
    		position:absolute; top:5px; right:5px; line-height: 16px; width: 42px; height: 38px; border-radius: 50%;
    	}
    	.img-box{
    		border: 0; width:33.33%; height:200px; box-sizing: border-box; position: relative;
    	}
    	#fileList, #fileList2{
    		display: none;
    	}
    </style>
</head>
<body>
	<h1>게시글 등록</h1>
	<form action="<c:url value="/post/insert"/>" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="po_title">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" id="content" name="po_content"></textarea>
		</div>
		<div class="form-group">
			<label>
				사진:
			</label>
			<div style="border: 1px solid gray; overflow-y: auto;">
				<div class="img-container d-flex flex-wrap align-items-center">
					<div class="mx-auto">
						<label for="fileList" class="btn btn-outline-success btn-insert-img">
							<i class="fi fi-br-plus align-items-center"></i>
						</label>
					</div>
				</div>
			</div>
			<label for="fileList2" class="btn btn-outline-success col-12 mt-3">
				<span>사진 추가(</span>
				<span class="img-count">0</span>
				<span>개)</span>
			</label>
			<input type="file" class="form-control" id="fileList" name="fileList" multiple="multiple" accept="image/*">
			<input type="file" class="form-control" id="fileList2" name="fileList2" multiple="multiple" accept="image/*">
		</div>
		<button class="btn btn-outline-info col-12">게시글 등록</button>
		<input type="hidden" name="po_co_num" value="${co_num}">
	</form>
	
	<script>
		$('#content').summernote({
		  placeholder: '게시글을 작성해주세요.',
		  tabsize: 2,
		  height: 400
		});
		
		function displayFileList(fileList){
			console.log(fileList);
			var count = fileList.length;
			$('.img-count').text(count);
			if(count > 0){
				for(var i = 0; i < count; i++){
					$('.img-container').children().remove();
					$('.img-container').removeClass('align-items-center')
					let fReader = new FileReader();
				    fReader.readAsDataURL(fileList[i]);
				    fReader.num = i;
				    fReader.onloadend = function(event){
				        var num = this.num;
				    	let path = event.target.result;
				        img = `
				        	<div class="img-box">
					        	<img src="\${path}" style="width:100%; height:100%">
						        	<button type="button" class="btn btn-outline-danger btn-delete-img" data-num="\${num}">
										<i class="fi fi-bs-cross"></i>
									</button>
								</img>
							</div>
				        `;
				        $('.img-container').append(img);
				    }
				}
			}
			else if(count == 0){
				$('.img-container').children().remove();
				$('.img-container').addClass('align-items-center')
		        btn = `
		        	<div class="mx-auto">
						<label for="fileList" class="btn btn-outline-success btn-insert-img">
							<i class="fi fi-br-plus align-items-center"></i>
						</label>
					</div>
		        `;
		        $('.img-container').append(btn);
			}
		}
		
		$(document).on("change", "#fileList", function(){
			displayFileList($("#fileList")[0].files);
		});
		
		const deleteFile = (fileNum) => {
		    const dataTransfer = new DataTransfer();
		    let files = $('#fileList')[0].files;
		    
		    let fileArray = Array.from(files);
		    fileArray.splice(fileNum, 1);
		    
		    fileArray.forEach(file => { dataTransfer.items.add(file); });
		    $('#fileList')[0].files = dataTransfer.files;
		}
		
		$(document).on("click", ".btn-delete-img", function(){
			let fileNum = $(this).data("num");
			console.log(fileNum);
			deleteFile(fileNum);
		    displayFileList($('#fileList')[0].files);
			$(this).parent().remove();
		});
		
		$(document).on("change", "#fileList2", function(){
			const dataTransfer = new DataTransfer();
		    
			let files = $('#fileList')[0].files;
		    let fileArray = Array.from(files);
		    
			let files2 = $('#fileList2')[0].files;
		    let fileArray2 = Array.from(files2);
		    
		    for(var i = 0; i < fileArray2.length; i++){
		    	fileArray.push(fileArray2[i]);
		    }
		    
		    fileArray.forEach(file => { dataTransfer.items.add(file); });
		    $('#fileList')[0].files = dataTransfer.files;
		    
			displayFileList($("#fileList")[0].files);
		});
		
    </script>
</body>
</html>