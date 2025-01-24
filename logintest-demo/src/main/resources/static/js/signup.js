document.addEventListener('DOMContentLoaded', () => {
    const signupForm = document.getElementById('signupForm');

    if (signupForm) {
        signupForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const name = document.getElementById('name').value;

            fetch('/auth/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({email, password, name})
            })
            .then(response => {
                if(response.ok) {
                    alert('회원가입 성공');
                    window.location.replace('/login'); // replace 사용
                } else {
                    return response.json().then(error => {
                        throw new Error(error.message || '회원가입 실패');
                    });
                }
            })
            .catch(error => {
                alert(error.message);
            });
        });
    }
});