
var tooLong = false;
var quill = new Quill('#editor-container', {
  modules: {
	  toolbar: [
	  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons

	  [{ 'header': 1 }, { 'header': 2 }],               // custom button values
	  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
	  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
	  [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
	  [{ 'direction': 'rtl' }],                         // text direction

	  [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
	  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

	  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
	  [{ 'font': [] }],
	  [{ 'align': [] }],

	  ['clean'],                                     // remove formatting button
	  ['image']
	  ]
  },
  theme: 'snow'
});

function isTooLong() {
   if(tooLong)
	   return false;
   else if(quill.getText().trim().length === 0)
	   {
	   $('#emptyAlert').css("display", "block");
	   return false;
	   }
   else
	   {
	   return true;
	   }
}
quill.on('text-change', function() {
  var about = document.querySelector('input[name=message]');
 $('#emptyAlert').css("display", "none");
  	if(quill.root.innerHTML.length > 2000000 )
  		{
  	   
  		tooLong = true;
  		$('#tooLongAlert').css("display", "block");
  		//$('#tooLongAlert').removeClass('invisible');
  		}
  	else
  		{
  		tooLong = false;
  		$('#tooLongAlert').css("display", "none");
  		//$('#tooLongAlert').addClass('invisible');
  		}
       about.value = quill.root.innerHTML;
	});