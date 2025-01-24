// 로그인 처리
function login(e) {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email, password})
    })
    .then(response => {
        if(response.ok) return response.json();
        return response.json().then(error => {
            throw new Error(error.message || '로그인 실패');
        });
    })
    .then(data => {
        localStorage.setItem('token', data.token);
        window.location.href = '/'; // 홈 페이지로 리다이렉트
    })
    .catch(error => {
        alert(error.message);
    });
}

// 회원가입 처리
function signup(e) {
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
            window.location.href = '/login'; // 로그인 페이지로 리다이렉트
        } else {
            return response.json().then(error => {
                throw new Error(error.message || '회원가입 실패');
            });
        }
    })
    .catch(error => {
        alert(error.message);
    });
}

// 페이지에 따라 이벤트 리스너 추가
document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');

    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            fetch('/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({email, password})
            })
            .then(response => {
                if(response.ok) return response.json();
                return response.json().then(error => {
                    throw new Error(error.message || '로그인 실패');
                });
            })
            .then(data => {
                localStorage.setItem('token', data.token);
                window.location.href = '/'; // 홈 페이지로 리다이렉트
            })
            .catch(error => {
                alert(error.message);
            });
        });
    }
});