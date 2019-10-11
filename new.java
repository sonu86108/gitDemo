private void registerUser(){
		String email=edtvEmail.getText().toString().trim();
		String pass=edtvPass.getText().toString().trim();
		
		if(email.isEmpty()){
			edtvEmail.setError("Email is rewuired!");
			edtvEmail.requestFocus();
			return;
		}
		if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
			edtvEmail.setError("Not a valid email");
			edtvEmail.requestFocus();
			return;
		}
		if(pass.isEmpty()){
			edtvPass.setError("Password is required");
			edtvPass.requestFocus();
			return;
		}
		if(pass.length()<6){
			edtvPass.setError("Password must not be less than 6 character!");
			edtvPass.requestFocus();
			return;
		}
		
		pb.setVisibility(View.VISIBLE);
		
		fbAuth.createUserWithEmailAndPassword(email,pass).
			addOnCompleteListener(new OnCompleteListener<AuthResult>(){

				@Override
				public void onComplete(Task<AuthResult> task){
					
					pb.setVisibility(View.VISIBLE);
					if(task.isSuccessful()){
						Toast.makeText(MainActivity.this,"User registration successfull",Toast.LENGTH_LONG).
						show();
						pb.setVisibility(View.GONE);
					}else if(task.getException() instanceof FirebaseAuthUserCollisionException){
						Toast.makeText(MainActivity.this,"User already exits",Toast.LENGTH_LONG).
							show();
						pb.setVisibility(View.GONE);
					}else{
						Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_LONG).
							show();
						pb.setVisibility(View.GONE);
					}
				}
				
			
		});
		
		
	}
