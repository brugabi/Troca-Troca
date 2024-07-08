"use client";
import { useState, useEffect } from 'react';
// import Image from 'next/image';
import Link from 'next/link';

const PostCards = () => {
    const [screenHeight, setScreenHeight] = useState(0);
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const response = await fetch('http://127.0.0.1:8080/api/anuncios/listarAnuncios');
                const data = await response.json();
                setPosts(data.Lista);
            } catch (error) {
                console.error('Erro ao buscar os posts:', error);
            }
        };

        fetchPosts();

        const updateHeight = () => {
            setScreenHeight(window.innerHeight);
        };

        updateHeight();
        window.addEventListener('resize', updateHeight);

        return () => window.removeEventListener('resize', updateHeight);
    }, []);

    return (
        <div className="flex flex-col w-full p-4" style={{ height: `calc(${screenHeight}px - 64px)`, overflowY: 'scroll' }}>
            <div className="grid grid-cols-2 md:grid-cols-3 gap-4">
                {posts.map(post => (
                    <div key={post.id} className="bg-white shadow-md rounded-md overflow-hidden">
                        <div className="p-4 items-start">
                            <div className="flex justify-start">
                                <span className=" text-gray-600">{post.criador.login}</span>
                            </div>
                            <h2 className="text-lg font-bold">{post.titulo}</h2>
                            <p className="text-gray-600">{post.descricao}</p>
                            <div className="flex flex-row justify-between items-center mt-2">
                                <Link href="#" className="text-blue-600 hover:underline">Ver detalhes</Link>
                                <div>
                                    <p className="text-gray-600">{post.departamento.nome}</p>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default PostCards;
