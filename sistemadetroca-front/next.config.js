module.exports = {
    images: {
        domains: ['via.placeholder.com'],
        // Remote patterns configuration
        // Add your remote patterns here if needed
        remotePatterns: [
            {
                protocol: 'http',
                hostname: '127.0.0.1',
                port: '8080',
                pathname: '/uploads/**',
            },
        ],
    },
};
